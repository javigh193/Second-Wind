package com.secondwind.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.secondwind.entity.OrderStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderDTO extends BaseDTO {
	
	@Enumerated(EnumType.STRING)
	OrderStatus status;
	
	private Float price; 
	
    private String createdOn;
	
	private String lastUpdatedOn;

	private String buyerFirstName;
	
	private String buyerLastName;
	
	private String buyerEmail;
	
	private String productTitle;
	
	private String productDescription;
	
	private Long productId;
}
