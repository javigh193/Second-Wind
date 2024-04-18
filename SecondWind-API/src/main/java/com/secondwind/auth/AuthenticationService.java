package com.secondwind.auth;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.secondwind.entity.Role;
import com.secondwind.entity.User;
import com.secondwind.repository.RoleRepository;
import com.secondwind.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final RoleRepository roleRepository;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest request) throws NotFoundException {
		var optDefaultRoles = roleRepository.findByName("ROLE_USER");
		List<Role> defaultRoles;
		if (optDefaultRoles.isPresent()) {
			defaultRoles = List.of(optDefaultRoles.get());
		} else {
			throw new NotFoundException();
		}
		
		User user = User.builder()
			.firstName(request.getFirstname())
			.lastName(request.getLastname())
			.dni(request.getDni())
			.email(request.getEmail())
			.password(passwordEncoder.encode(request.getPassword()))
			.roles(defaultRoles)
			.enabled(true)
			.build();
		
		userRepository.save(user);
		
		var jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponse.builder()
			.token(jwtToken)
			.build();
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) throws NotFoundException {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getEmail(),
				request.getPassword()
			)
		);
		
		var optUser = userRepository.findByEmail(request.getEmail());
		
		User user;
		
		if (optUser.isPresent()) {
			user = optUser.get();
		} else {
			throw new NotFoundException();
		}

		var jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	
}
