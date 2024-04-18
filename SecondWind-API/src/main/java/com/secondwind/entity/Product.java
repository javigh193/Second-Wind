package com.secondwind.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="product")
public class Product extends BaseEntity {
	
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	
	@NotBlank
	@NotNull
	private String description;
	
	@Positive
	@NotNull
	private Float price; 
	
	@NotBlank
	@NotNull
	private String title;

	@CreationTimestamp
    private Instant createdOn;
	 
	@UpdateTimestamp
	private Instant lastUpdatedOn;
	
	@ManyToOne
	@JoinColumn(name="fk_usuario")
	private User seller;
	
}
	
	
