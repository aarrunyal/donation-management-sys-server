package com.jwtauthentication.JwtAuthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtauthentication.JwtAuthentication.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
	Optional <User> findByFirstName(String email);
}
