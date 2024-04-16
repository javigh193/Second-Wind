package com.secondwind.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.secondwind.auth.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class ApplicationConf {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
//	private final UserRepository userRepository;
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return username -> userRepository.findByEmail(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
//	}
	
	@Bean
	public RoleHierarchy roleHierarchy() {
	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	    //ROLE_STAFF \n ROLE_STAFF 
	    String hierarchy = "ROLE_ADMIN > ROLE_USER";
	    roleHierarchy.setHierarchy(hierarchy);
	    return roleHierarchy;
	}
	
//	@Bean
//	public DefaultWebSecurityExpressionHandler customWebSecurityExpressionHandler() {
//	    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
//	    expressionHandler.setRoleHierarchy(roleHierarchy());
//	    return expressionHandler;
//	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception {
		return conf.getAuthenticationManager();
	}
	
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
