package com.secondwind.dto.converter;

import org.springframework.stereotype.Component;

import com.secondwind.dto.PrivilegeDTO;
import com.secondwind.entity.Privilege;

@Component
public class PrivilegeConverter extends BaseConverterImpl<PrivilegeDTO, Privilege> {
	
	@Override
	public Privilege dtoToEntity(PrivilegeDTO privilegeDTO) {
		Privilege privilege = new Privilege();
		privilege.setName(privilegeDTO.getName());
		return privilege;	
	}
	
	@Override
	public Privilege dtoToEntity(PrivilegeDTO privilegeDTO, Privilege privilege) {
		privilege.setName(privilegeDTO.getName());
		return privilege;	
	}
	
	@Override
	public PrivilegeDTO entityToDTO(Privilege privilege) {
		PrivilegeDTO privilegeDTO = new PrivilegeDTO();
		privilegeDTO.setId(privilege.getId());
		privilegeDTO.setName(privilege.getName());
		return privilegeDTO;
	}
}
