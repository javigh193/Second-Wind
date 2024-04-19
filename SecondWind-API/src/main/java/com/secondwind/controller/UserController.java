package com.secondwind.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.secondwind.dto.UserDTO;
import com.secondwind.dto.converter.UserConverter;
import com.secondwind.entity.User;
import com.secondwind.service.impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/users")
public class UserController
	extends BaseController<UserDTO, User, UserConverter, UserServiceImpl> {

	
	@Operation(
		summary = "Updates the register of the user, adding the indicated role", 
		description = "Takes the the id provided in the url to fetch the user and adds the role corresponding with the id indicated in the query parameter. Returns the resource representation after presistance")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully updated"), 
        @ApiResponse(responseCode = "404", description = "Not found - There where no registers for the user or the role")
    })
	@PutMapping("/{userId}/add-role")
	public ResponseEntity<?> 
		addRole(@PathVariable Long userId,  
				 @RequestParam @Positive @NotNull Long roleId) {
			return service.addRole(userId, roleId);
	}
	
	@Operation(
		summary = "Updates the register of the user, removing the indicated role", 
		description = "Takes the the id provided in the url to fetch the user and removes the role corresponding with the id indicated in the query parameter. Returns the resource representation after presistance")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully updated"), 
        @ApiResponse(responseCode = "404", description = "Not found - There where no registers for the user or the role")
    })
	@PutMapping("/{userId}/remove-role")
	public ResponseEntity<?> 
		removeRole(@PathVariable Long userId,  
				 @RequestParam @Positive @NotNull Long roleId) {
			return service.removeRole(userId, roleId);
	}
	
	@Operation(
		summary = "Updates the register of the user, adding the indicated address", 
		description = "Takes the the id provided in the url to fetch the user and adds the address corresponding with the id indicated in the query parameter. Returns the resource representation after presistance")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully updated"), 
        @ApiResponse(responseCode = "404", description = "Not found - There where no registers for the user or the address")
    })
	@PutMapping("/{userId}/add-address")
	public ResponseEntity<?> 
		addAddress(@PathVariable Long userId,  
				 @RequestParam @Positive @NotNull Long addressId) {
			return service.addAddress(userId, addressId);
	}
	
	
	@Operation(
		summary = "Updates the register of the user, removing its address", 
		description = "Takes the the id provided in the url to fetch the user and removes its address. Returns the resource representation after presistance")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully updated"), 
        @ApiResponse(responseCode = "404", description = "Not found - There where no registers for the user indicated")
    })
	@PutMapping("/{userId}/remove-address")
	public ResponseEntity<?> 
		removeRole(@PathVariable Long userId) {
			return service.removeAddress(userId);
	}
}
