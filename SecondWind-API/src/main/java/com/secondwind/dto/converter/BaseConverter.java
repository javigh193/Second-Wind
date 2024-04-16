package com.secondwind.dto.converter;

import org.springframework.data.repository.NoRepositoryBean;

import com.secondwind.dto.BaseDTO;
import com.secondwind.entity.BaseEntity;

@NoRepositoryBean
public interface BaseConverter<DTO extends BaseDTO, E extends BaseEntity> {

	public E dtoToEntity(DTO dto);
	public E dtoToEntity(DTO dto, E entity);
	public DTO entityToDTO(E entity);
}
