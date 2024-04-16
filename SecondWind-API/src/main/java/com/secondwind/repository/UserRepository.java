package com.secondwind.repository;

import java.util.Optional;

import com.secondwind.entity.User;

public interface UserRepository extends BaseRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
}
