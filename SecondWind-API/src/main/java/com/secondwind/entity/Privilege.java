package com.secondwind.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="privilege")
public class Privilege extends BaseEntity {

	@NotNull(message="Name is required")
	@Size(
		min=3, 
		max=20,
		message="Name must be between 3 and 20 characters long"
	)
	@Column(unique=true)
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;
}
