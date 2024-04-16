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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="custom_user")
public class User extends BaseEntity {
	
	@Email(message="Email must have a valid format")
	@NotNull(message="Email is required")
	@Column(unique=true)
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
	
	@NotNull(message="Enabled is required")
	private boolean enabled;
	
	@OneToOne
	@JoinColumn(name="fk_address")
	private Address address;
	
	@ManyToMany
	@JoinTable(
		name = "user_roles",
		joinColumns = @JoinColumn(name="user_id", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id")
	)
	private List<Role> roles;
}
