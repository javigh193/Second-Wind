package com.secondwind.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.secondwind.dto.BaseDTO;
import com.secondwind.dto.converter.BaseConverter;
import com.secondwind.dto.converter.BaseConverterImpl;
import com.secondwind.entity.BaseEntity;
import com.secondwind.repository.BaseRepository;
import com.secondwind.service.BaseService;

import jakarta.validation.Valid;

public abstract class BaseServiceImpl<DTO extends BaseDTO, ID extends Serializable, E extends BaseEntity, C extends BaseConverterImpl<DTO, E>> 
	implements BaseService<DTO, ID> {
	
	protected BaseRepository<E, ID> baseRepository;
	
	protected BaseConverter<DTO, E> baseConverter;
	
	protected BaseServiceImpl(BaseRepository<E, ID> baseRepository, BaseConverter<DTO, E> baseConverter) {
		this.baseRepository = baseRepository;
		this.baseConverter = baseConverter;
	}
	
	public ResponseEntity<?> save(@Valid @RequestBody DTO dto) {
		try {
			E entity = baseRepository.save(baseConverter.dtoToEntity(dto));
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(baseConverter.entityToDTO(entity));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}		
	}
	
	@Override
	public ResponseEntity<?> findAll() {
		List<E> entities= baseRepository.findAll();
		if (entities.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("No hay registros del tipo solicitado");	
		} else {
			List<DTO> dtos = new ArrayList<>();
			for (E entity : entities) {
				DTO dto = baseConverter.entityToDTO(entity);
				dtos.add(dto);
			}
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(dtos);
		}
	}
	
	@Override
	public ResponseEntity<?> findById(ID id) {
		Optional<E> optE = baseRepository.findById(id);
		if (optE.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(baseConverter.entityToDTO(optE.get()));
		} else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No register was found");	
		}
	}
	
	@Override
	public ResponseEntity<?> update(ID id, DTO dto) {
		Optional<E> optE = baseRepository.findById(id);
		if (optE.isPresent()) {
			E entity = optE.get();
			entity = baseRepository.save(baseConverter.dtoToEntity(dto, entity));
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(baseConverter.entityToDTO(entity));
		} else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("No hay registros del tipo solicitado");
		}
	}
	
	@Override
	public ResponseEntity<?> delete(ID id) {
		baseRepository.deleteById(id);
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(null);
	}

}
