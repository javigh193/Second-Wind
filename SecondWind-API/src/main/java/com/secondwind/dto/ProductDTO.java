package com.secondwind.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductDTO extends BaseDTO {
	
	@NotBlank(message="Title is required")
	@NotNull(message="Title is required")
	private String title;
	
	@NotBlank(message="Description is required")
	@NotNull(message="Description is required")
	private String description;
	
	@Positive(message="Price must be positive")
	@NotNull(message="Price is required")
	private Float price; 
	
    private String createdOn;
	 
	private String lastUpdatedOn;
	
	private String sellerEmail;
}
