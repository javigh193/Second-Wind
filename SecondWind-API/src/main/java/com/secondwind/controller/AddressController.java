package com.secondwind.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondwind.dto.AddressDTO;
import com.secondwind.dto.converter.AddressConverter;
import com.secondwind.entity.Address;
import com.secondwind.service.impl.AddressServiceImpl;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/addresses")
public class AddressController 
	extends BaseController<AddressDTO, Address, AddressConverter, AddressServiceImpl> {
	
}
