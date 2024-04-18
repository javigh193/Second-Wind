package com.secondwind.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.secondwind.entity.OrderStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderCreationDTO extends BaseDTO {

	@Positive(message="Identificación del comprador debe ser positiva")
	@NotNull(message="Identificación del comprador es requerida")
	private Long buyerId;
	
	@Positive(message="Identificación del producto debe ser positiva")
	@NotNull(message="Identificación del producto es requerida")
	private Long productId;
}
