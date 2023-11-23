package com.jwtauthentication.JwtAuthentication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jwtauthentication.JwtAuthentication.model.User;
import com.jwtauthentication.JwtAuthentication.repository.UserRepository;
//implements UserDetailsService 
public class CustomUserDetailsService {

//	private UserRepository userRepository;
//
//	public CustomUserDetailsService(UserRepository repository) {
//		this.userRepository = userRepository;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		User user = userRepository.findUserByEmail(email);
//		List<String> roles = new ArrayList();
//		roles.add("USER");
//		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
//				.password(user.getPassword()).roles(roles.toArray(new String[0])).build();
//		return userDetails;
//	}

}
