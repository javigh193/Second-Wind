package com.secondwind.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondwind.dto.RoleDTO;
import com.secondwind.dto.converter.BaseConverter;
import com.secondwind.dto.converter.RoleConverter;
import com.secondwind.entity.Privilege;
import com.secondwind.entity.Role;
import com.secondwind.repository.BaseRepository;
import com.secondwind.repository.PrivilegeRepository;
import com.secondwind.repository.RoleRepository;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDTO, Long, Role, RoleConverter> {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
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
					.body("There where no registers for that entity");	
		}
	}
	
	public ResponseEntity<?> addPrivilege(Long privilegeId, Long roleId) {
		Optional<Privilege> optPrivilege = privilegeRepository.findById(privilegeId);
		if (optPrivilege.isPresent()) {
			Optional<Role> optRole = roleRepository.findById(roleId);
			if (optRole.isPresent()) {
				Privilege privilege = optPrivilege.get();
				Role role = optRole.get();
				if (!role.getPrivileges().contains(privilege)) {
					role.getPrivileges().add(privilege);
					roleRepository.save(role);
				}
			}
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body("Privilegio concedido con éxito");
		} else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("There where no registers for that entity");
		}
	}
	
	public ResponseEntity<?> removePrivilege(Long roleId, Long privilegeId) {
		Optional<Privilege> optPrivilege = privilegeRepository.findById(privilegeId);
		if (optPrivilege.isPresent()) {
			Optional<Role> optRole = roleRepository.findById(roleId);
			if (optRole.isPresent()) {
				Privilege privilege = optPrivilege.get();
				Role role = optRole.get();
				role.getPrivileges().remove(privilege);
				roleRepository.save(role);
			}
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body("Privilegio revocado con éxito");
			} else {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("There where no registers for that entity");
			}
		}

}
