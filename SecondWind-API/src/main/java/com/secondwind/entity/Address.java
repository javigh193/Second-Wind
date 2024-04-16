package com.secondwind.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="address")
public class Address extends BaseEntity {
	
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
