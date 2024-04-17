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

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/users")
public class UserController
	extends BaseController<UserDTO, User, UserConverter, UserServiceImpl> {

	@PutMapping("/{userId}/add-role")
	public ResponseEntity<?> 
		addRole(@PathVariable Long userId,  
				 @RequestParam @Positive @NotNull Long roleId) {
			return service.addRole(userId, roleId);
	}
	
	@PutMapping("/{userId}/remove-role")
	public ResponseEntity<?> 
		removeRole(@PathVariable Long userId,  
				 @RequestParam @Positive @NotNull Long roleId) {
			return service.removeRole(userId, roleId);
	}
	
	@PutMapping("/{userId}/add-address")
	public ResponseEntity<?> 
		addAddress(@PathVariable Long userId,  
				 @RequestParam @Positive @NotNull Long addressId) {
			return service.addAddress(userId, addressId);
	}
	
	@PutMapping("/{userId}/remove-address")
	public ResponseEntity<?> 
		removeRole(@PathVariable Long userId) {
			return service.removeAddress(userId);
	}
}
