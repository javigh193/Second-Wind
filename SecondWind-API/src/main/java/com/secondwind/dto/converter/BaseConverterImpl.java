package com.secondwind.dto.converter;

import com.secondwind.dto.BaseDTO;
import com.secondwind.entity.BaseEntity;

public abstract class BaseConverterImpl<DTO extends BaseDTO, E extends BaseEntity> implements BaseConverter<DTO, E> {

	@Override
	public E dtoToEntity(DTO dto) {
		return null;
	}
	
	@Override
	public E dtoToEntity(DTO dto, E entity) {
		return null;
	}

	@Override
	public DTO entityToDTO(E entity) {
		return null;
	}

}
