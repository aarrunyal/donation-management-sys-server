package com.jwtauthentication.JwtAuthentication.repository;

import org.springframework.stereotype.Repository;

import com.jwtauthentication.JwtAuthentication.model.User;

@Repository
public class UserRepository {

	public User findUserByEmail(String email) {
		return new User(email, "12345", "Arun", "Aryal");
	}
}
