package com.secondwind.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="custom_user")
public class User extends BaseEntity implements UserDetails {
	
	@Email(message="Email must have a valid format")
	@NotNull(message="Email is required")
	@Column(unique=true)
	private String email;
	
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
