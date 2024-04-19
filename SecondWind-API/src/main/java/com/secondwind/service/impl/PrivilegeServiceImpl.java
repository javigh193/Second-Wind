package com.secondwind.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secondwind.dto.PrivilegeDTO;
import com.secondwind.dto.converter.BaseConverter;
import com.secondwind.dto.converter.PrivilegeConverter;
import com.secondwind.entity.Privilege;
import com.secondwind.repository.BaseRepository;
import com.secondwind.repository.PrivilegeRepository;

@Service
public class PrivilegeServiceImpl extends BaseServiceImpl<PrivilegeDTO, Long, Privilege, PrivilegeConverter> {

	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private PrivilegeConverter privilegeConverter;
	
	public PrivilegeServiceImpl(BaseRepository<Privilege, Long> baseRepository, BaseConverter<PrivilegeDTO, Privilege> baseConverter) {
		super(baseRepository, baseConverter);
	}
	
}
