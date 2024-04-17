package com.secondwind.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.secondwind.dto.RoleDTO;
import com.secondwind.dto.converter.RoleConverter;
import com.secondwind.entity.Role;
import com.secondwind.service.impl.RoleServiceImpl;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/roles")
public class RoleController 
	extends BaseController<RoleDTO, Role, RoleConverter, RoleServiceImpl> {

	@GetMapping("/search")
	public ResponseEntity<?> findByName(@RequestParam String name) {
		return service.findByName(name);
	}
	
	@PutMapping("/{roleId}/add-privilege")
	public ResponseEntity<?> 
		addPrivilege(@PathVariable Long roleId,  
				 @RequestParam @Positive @NotNull Long privilegeId) {
		return service.addPrivilege(roleId, privilegeId);
	}
	
	@PutMapping("/{roleId}/remove-privilege")
	public ResponseEntity<?> 
		removePrivilege(@PathVariable Long roleId,  
				 @RequestParam @Positive @NotNull Long privilegeId) {
		return service.removePrivilege(roleId, privilegeId);
	}
}

