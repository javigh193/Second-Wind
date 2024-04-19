package com.secondwind.auth;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService service;
	
	@Operation(
		summary = "Creates a new register of user and returns a JWT", 
		description = "Takes the body of the request to create and register an instance of the user. Returns the resource representation after presistance, alogn with a JWT.")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created"), 
        @ApiResponse(responseCode = "400", description = "Bad request - Something about the request was wrong. Please, make sure that everything conforms with the documentation for this operation.")
    })
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws NotFoundException {
		return ResponseEntity.ok(service.register(request));
	}
	
	@Operation(
		summary = "Authenticates the user and returns a JWT", 
		description = "Takes the body of the request to authenticate the user. Returns a JWT.")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully authenticated"), 
        @ApiResponse(responseCode = "400", description = "Bad request - Something about the request was wrong. Please, make sure that everything conforms with the documentation for this operation.")
    })
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) throws NotFoundException {
		return ResponseEntity.ok(service.authenticate(request));
	}
	
}
