package com.jwtauthentication.JwtAuthentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtauthentication.JwtAuthentication.model.User;
import com.jwtauthentication.JwtAuthentication.repository.UserRepository;
import com.jwtauthentication.JwtAuthentication.utils.UserInfoDetails;

@Service
public class UserInfoService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private PasswordEncoder encoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userDetail  = userRepository.findByEmail(username);
		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
	}

	public String addUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "User Added Successfully";
	}
}
