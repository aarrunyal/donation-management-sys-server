package com.jwtauthentication.JwtAuthentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.jwtauthentication.JwtAuthentication.config.UserInfoDetails;
import com.jwtauthentication.JwtAuthentication.model.User;
import com.jwtauthentication.JwtAuthentication.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { 
  
//        Optional<User> userDetail = repository.findByEmail(email); 
		Optional<User> userDetail = repository.findByEmail(email); 
        // Converting userDetail to UserDetails 
        return userDetail.map(UserInfoDetails::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email)); 
    } 
	
	
	public String addUser(User userInfo) { 
        userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword())); 
        repository.save(userInfo); 
        return "User Added Successfully"; 
    } 
}
