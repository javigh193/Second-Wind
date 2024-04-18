package com.secondwind.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConf {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private final AuthenticationProvider authenticationProvider;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authRequest ->
					authRequest
						.requestMatchers("/api/v1/auth/**").permitAll()
						.requestMatchers("api/v1/products/forsale").permitAll()
						.requestMatchers("/v3/api-docs/**").permitAll()
						.requestMatchers("/swagger-ui/**").permitAll()
//						.requestMatchers(HttpMethod.GET).hasRole("USER")
//						.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//						.requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
//						.requestMatchers(HttpMethod.POST).hasRole("ADMIN")
//						.requestMatchers(HttpMethod.PATCH).hasRole("ADMIN")
						.anyRequest().authenticated()
				)
				.sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
}
