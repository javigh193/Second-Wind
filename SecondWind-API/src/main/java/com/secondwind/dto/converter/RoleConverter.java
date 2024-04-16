package com.secondwind.dto.converter;

import org.springframework.stereotype.Component;

import com.secondwind.dto.RoleDTO;
import com.secondwind.entity.Role;

@Component
public class RoleConverter extends BaseConverterImpl<RoleDTO, Role> {

	@Override
	public Role dtoToEntity(RoleDTO roleDTO) {
		Role role = new Role();
		role.setName(roleDTO.getName());
		return role;	
	}
	
	@Override
	public Role dtoToEntity(RoleDTO roleDTO, Role role) {
		role.setName(roleDTO.getName());
		return role;	
	}
	
	@Override
	public RoleDTO entityToDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());
		return roleDTO;
	}
	
}
