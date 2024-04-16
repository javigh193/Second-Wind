package com.secondwind.repository;

import java.util.Optional;

import com.secondwind.entity.Role;

public interface RoleRepository extends BaseRepository<Role, Long> {
	
	Optional<Role> findByName(String name);
}
