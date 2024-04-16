package com.secondwind.dto.converter;

import org.springframework.stereotype.Component;

import com.secondwind.dto.AddressDTO;
import com.secondwind.entity.Address;

@Component
public class AddressConverter extends BaseConverterImpl<AddressDTO, Address> {

	@Override
	public Address dtoToEntity(AddressDTO addressDTO) {
		Address address = new Address();
		address.setStreet(addressDTO.getStreet());
		address.setNumber(addressDTO.getNumber());
		address.setCode(addressDTO.getCode());
		return address;	
	}
	
	@Override
	public Address dtoToEntity(AddressDTO addressDTO, Address address) {
		address.setStreet(addressDTO.getStreet());
		address.setNumber(addressDTO.getNumber());
		address.setCode(addressDTO.getCode());
		return address;	
	}
	
	@Override
	public AddressDTO entityToDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(address.getId());
		addressDTO.setStreet(address.getStreet());
		addressDTO.setNumber(address.getNumber());
		addressDTO.setCode(address.getCode());
		return addressDTO;
	}
}
