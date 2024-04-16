package com.secondwind.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondwind.dto.PrivilegeDTO;
import com.secondwind.dto.converter.PrivilegeConverter;
import com.secondwind.entity.Privilege;
import com.secondwind.service.impl.PrivilegeServiceImpl;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/privileges")
public class PrivilegeControler 
	extends BaseController<PrivilegeDTO, Privilege, PrivilegeConverter, PrivilegeServiceImpl> {

}
