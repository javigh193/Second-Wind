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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/roles")
public class RoleController 
	extends BaseController<RoleDTO, Role, RoleConverter, RoleServiceImpl> {

	@Operation(
			summary = "Get all the registers available for the roles with a matching name", 
			description = "Retrieves a list of roles that match the name provided as a query parameter")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
        @ApiResponse(responseCode = "404", description = "Not found - There where no registers for roles that matched the given name")
    })
	@GetMapping("/search")
	public ResponseEntity<?> findByName(@RequestParam String name) {
		return service.findByName(name);
	}
	
	
	@Operation(
			summary = "Updates the register of the role, adding the privilege indicated", 
			description = "Takes the the id provided in the url to fetch the role and adds the privilege corresponding with the id provided in the query parameter. Returns the resource representation after presistance.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Successfully added the privilege"), 
	    @ApiResponse(responseCode = "400", description = "Bad request - Something about the request was wrong. Please, make sure that everything conforms with the documentation for this operation."),
	    @ApiResponse(responseCode = "404", description = "Not found - There where no registers for that role")
	})
	@PutMapping("/{roleId}/add-privilege")
	public ResponseEntity<?> 
		addPrivilege(@PathVariable Long roleId,  
				 @RequestParam @Positive @NotNull Long privilegeId) {
		return service.addPrivilege(roleId, privilegeId);
	}
	
	
	@Operation(
			summary = "Updates the register of the role, removing the privilege indicated.", 
			description = "Takes the the id provided in the url to fetch the role and removes the privilege corresponding with the id provided in the query parameter. Returns the resource representation after presistance")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully updated"), 
        @ApiResponse(responseCode = "400", description = "Bad request - Something about the request was wrong. Please, make sure that everything conforms with the documentation for this operation"),
        @ApiResponse(responseCode = "404", description = "Not found - There where no registers for that role")
    })
	@PutMapping("/{roleId}/remove-privilege")
	public ResponseEntity<?> 
		removePrivilege(@PathVariable Long roleId,  
				 @RequestParam @Positive @NotNull Long privilegeId) {
		return service.removePrivilege(roleId, privilegeId);
	}
}

