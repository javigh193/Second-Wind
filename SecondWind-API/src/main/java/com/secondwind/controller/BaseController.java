package com.secondwind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.secondwind.dto.BaseDTO;
import com.secondwind.dto.converter.BaseConverterImpl;
import com.secondwind.entity.BaseEntity;
import com.secondwind.service.impl.BaseServiceImpl;

import jakarta.validation.Valid;

public abstract class BaseController
	<
		DTO extends BaseDTO, 
		E extends BaseEntity, 
		C extends BaseConverterImpl<DTO, E>,  
		S extends BaseServiceImpl<DTO, Long, E, C>
	> {
	
	@Autowired
	protected S service;
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		return service.findAll();
	}
	
	@PostMapping("")
	public ResponseEntity<?> save(@Valid @RequestBody DTO dto) {
		return service.save(dto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody DTO dto) {
		return service.update(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return service.delete(id);
	}
	
}