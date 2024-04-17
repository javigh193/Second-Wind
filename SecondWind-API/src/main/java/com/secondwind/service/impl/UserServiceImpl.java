package com.secondwind.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondwind.dto.UserDTO;
import com.secondwind.dto.converter.BaseConverter;
import com.secondwind.dto.converter.UserConverter;
import com.secondwind.entity.Address;
import com.secondwind.entity.Role;
import com.secondwind.entity.User;
import com.secondwind.repository.AddressRepository;
import com.secondwind.repository.BaseRepository;
import com.secondwind.repository.RoleRepository;
import com.secondwind.repository.UserRepository;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO, Long, User, UserConverter> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	
	public UserServiceImpl(BaseRepository<User, Long> baseRepository, BaseConverter<UserDTO, User> baseConverter) {
		super(baseRepository, baseConverter);
	}
	
	public ResponseEntity<?> addRole(Long userId, Long roleId) {
		Optional<Role> optRole = roleRepository.findById(roleId);
		if (optRole.isPresent()) {
			Optional<User> optUser = userRepository.findById(userId);
			if (optUser.isPresent()) {
				User user = optUser.get();
				Role role = optRole.get();
				if (!user.getRoles().contains(role)) {
					user.getRoles().add(role);
					userRepository.save(user);
				}
			}
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Role añadido con éxito");
		} else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("No hay registros del tipo solicitado");
		}
	}
	
	public ResponseEntity<?> removeRole(Long userId, Long roleId) {
		Optional<Role> optRole = roleRepository.findById(roleId);
		if (optRole.isPresent()) {
			Optional<User> optUser = userRepository.findById(userId);
			if (optUser.isPresent()) {
				User user = optUser.get();
				Role role = optRole.get();
				user.getRoles().remove(role);
				userRepository.save(user);
			}
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Role eliminado con éxito");
		} else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("No hay registros del tipo solicitado");
		}
	}
	
	public ResponseEntity<?> addAddress(Long userId, Long addressId) {
		Optional<Address> optAddress = addressRepository.findById(addressId);
		if (optAddress.isPresent()) {
			Optional<User> optUser = userRepository.findById(userId);
			if (optUser.isPresent()) {
				User user = optUser.get();
				Address address = optAddress.get();
				if (user.getAddress() != address) {
					user.setAddress(address);
					userRepository.save(user);
				}
			}
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Dirección actualizada con éxito");
		} else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("No hay registros del tipo solicitado");
		}
	}
	
	public ResponseEntity<?> removeAddress(Long userId) {
		Optional<User> optUser = userRepository.findById(userId);
		if (optUser.isPresent()) {
			User user = optUser.get();
			user.setAddress(null);
			userRepository.save(user);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Dirección eliminada con éxito");
		} else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("No hay registros del tipo solicitado");
		}
	}

}
