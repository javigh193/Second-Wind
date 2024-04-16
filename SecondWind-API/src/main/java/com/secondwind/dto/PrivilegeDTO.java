package com.secondwind.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrivilegeDTO extends BaseDTO {

	@NotNull(message="Name is required")
	@Size(
		min=3, 
		max=20,
		message="Name must be between 3 and 20 characters long"
	)
    private String name;
}
