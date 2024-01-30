package com.donationmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donationmanagementsystem.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
	Optional <User> findByFirstName(String email);
	
	User getByEmail(String email);
	
	Integer countByEmail(String email);
}
