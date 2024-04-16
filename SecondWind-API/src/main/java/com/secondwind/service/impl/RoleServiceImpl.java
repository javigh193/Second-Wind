package com.secondwind.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondwind.dto.RoleDTO;
import com.secondwind.dto.converter.BaseConverter;
import com.secondwind.dto.converter.RoleConverter;
import com.secondwind.entity.Role;
import com.secondwind.repository.BaseRepository;
import com.secondwind.repository.RoleRepository;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDTO, Long, Role, RoleConverter> {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;
	
	public RoleServiceImpl(BaseRepository<Role, Long> baseRepository, BaseConverter<RoleDTO, Role> baseConverter) {
		super(baseRepository, baseConverter);
	}

	public ResponseEntity<?> findByName(String name) {
		Optional<Role> optRole = roleRepository.findByName(name);
		if (optRole.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(roleConverter.entityToDTO(optRole.get()));
		} else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No register was found");	
		}
	}

}
