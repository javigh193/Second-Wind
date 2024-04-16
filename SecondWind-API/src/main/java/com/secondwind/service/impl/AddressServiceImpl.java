package com.secondwind.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secondwind.dto.AddressDTO;
import com.secondwind.dto.converter.AddressConverter;
import com.secondwind.dto.converter.BaseConverter;
import com.secondwind.entity.Address;
import com.secondwind.repository.AddressRepository;
import com.secondwind.repository.BaseRepository;

@Service
public class AddressServiceImpl extends BaseServiceImpl<AddressDTO, Long, Address, AddressConverter> {

	@Autowired
	private AddressRepository roleRepository;
	
	@Autowired
	private AddressConverter roleConverter;

	public AddressServiceImpl(BaseRepository<Address, Long> baseRepository, BaseConverter<AddressDTO, Address> baseConverter) {
		super(baseRepository, baseConverter);
	}
}
