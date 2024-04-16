package com.secondwind.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDTO extends BaseDTO {
	
	@Email(message="Email must have a valid format")
	@NotNull(message="Email is required")
	private String email;
	
	@Pattern(
			regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
			message="Password must be at least 8 characters long and have at least one number, one letter and one special characater"
	)
	@NotNull(message="Password is required")
	private String password;
	
	@NotNull(message="First name is required")
	@Size(
		min=2, 
		max=100,
		message="First name must be between 2 and 100 characters long"
	)
	private String firstName;
	
	@NotNull(message="Last name is required")
	@Size(
		min=2, 
		max=100,
		message="Last name must be between 2 and 100 characters long"
	)
	private String lastName;
	
	@NotNull(message="Dni is required")
	@Size(
			min=9,
			max=9,
			message="Dni must have a valid format"
	)
	@Pattern(
			regexp="^\\d{8}[A-Z]$",
			message="Dni must have a valid format"
	)
	@Column(unique=true)
	private String dni;
	
}
