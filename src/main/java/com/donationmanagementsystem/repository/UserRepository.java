package com.donationmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donationmanagementsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	Optional<User> findByEmail(String email);

    List<User> findAllByEmail(String email);

    Optional<User> findByEmailAndVerified(String email);
}
