package com.secondwind.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondwind.dto.UserDTO;
import com.secondwind.dto.converter.UserConverter;
import com.secondwind.entity.User;
import com.secondwind.service.impl.UserServiceImpl;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/users")
public class UserController
	extends BaseController<UserDTO, User, UserConverter, UserServiceImpl> {

}
