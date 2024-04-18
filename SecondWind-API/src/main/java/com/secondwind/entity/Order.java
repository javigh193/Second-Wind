package com.secondwind.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="order_for_product")
public class Order extends BaseEntity {

	@Enumerated(EnumType.STRING)
	OrderStatus status;
	
	@Positive(message="El precio debe ser positivo")
	@NotNull(message="El precio es requerido")
	private Float price; 

	@CreationTimestamp
    private Instant createdOn;
	 
	@UpdateTimestamp
	private Instant lastUpdatedOn;
	
	@ManyToOne
	@JoinColumn(name="fk_usuario")
	private User buyer;
	
	@OneToOne
	@JoinColumn(name="fk_product")
	private Product product;
}
