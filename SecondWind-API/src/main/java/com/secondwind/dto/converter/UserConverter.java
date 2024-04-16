package com.secondwind.dto.converter;

import org.springframework.stereotype.Component;

import com.secondwind.dto.UserDTO;
import com.secondwind.entity.User;

@Component
public class UserConverter extends BaseConverterImpl<UserDTO, User> {

	@Override
	public User dtoToEntity(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setDni(userDTO.getDni());
		return user;	
	}
	
	@Override
	public User dtoToEntity(UserDTO userDTO, User user) {
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setDni(userDTO.getDni());
		return user;	
	}
	
	@Override
	public UserDTO entityToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setDni(user.getDni());
		return userDTO;
	}
}
