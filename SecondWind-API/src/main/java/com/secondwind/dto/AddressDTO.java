package com.secondwind.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class AddressDTO extends BaseDTO {

	@NotNull(message="Address street name is required")
	@Size(
		min=2, 
		max=100,
		message="Street name must be between 2 and 100 characters long"
	)
	private String street;
	
	@NotNull(message="Address number is required")
	@Positive(message="Address number must be positive")
	private Integer number;
	
	@NotNull(message="Address code is required")
	@Positive(message="Address code must be positive")
	private Integer code;
}
