package com.secondwind.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name="role")
public class Role extends BaseEntity {
	
	@NotNull(message="Name is required")
	@Size(
		min=3, 
		max=20,
		message="Name must be between 3 and 20 characters long"
	)
	@Column(unique=true)
	private String name;
	
	@ManyToMany(mappedBy ="roles")
	private List<User> users;
	
	@ManyToMany
	@JoinTable(
		name = "roles_privileges",
		joinColumns = @JoinColumn(name="role_id", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn(name="privilege_id", referencedColumnName="id")
	)
	private List<Privilege> privileges;
}
