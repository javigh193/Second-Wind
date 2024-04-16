package com.secondwind.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secondwind.dto.UserDTO;
import com.secondwind.dto.converter.BaseConverter;
import com.secondwind.dto.converter.UserConverter;
import com.secondwind.entity.User;
import com.secondwind.repository.BaseRepository;
import com.secondwind.repository.UserRepository;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO, Long, User, UserConverter> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	public UserServiceImpl(BaseRepository<User, Long> baseRepository, BaseConverter<UserDTO, User> baseConverter) {
		super(baseRepository, baseConverter);
	}

}
