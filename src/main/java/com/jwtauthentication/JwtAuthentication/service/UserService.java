package com.jwtauthentication.JwtAuthentication.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.jwtauthentication.JwtAuthentication.config.UserInfoDetails;
import com.jwtauthentication.JwtAuthentication.exception.ResourceAlreadyExistException;
import com.jwtauthentication.JwtAuthentication.exception.ResourceNotFoundException;
import com.jwtauthentication.JwtAuthentication.model.User;
import com.jwtauthentication.JwtAuthentication.model.Dto.UserDto;
import com.jwtauthentication.JwtAuthentication.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private ModelMapper mapper;
	
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
	
	
	public UserDto addUser(UserDto userInfo) {
		int totalUser = this.repository.countByEmail(userInfo.getEmail());
		System.out.println(totalUser);
		if(totalUser > 0)
			throw new ResourceAlreadyExistException("User", "email", userInfo.getEmail());
		User user = this.mapper.map(userInfo, User.class);
		user.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
        user = this.repository.save(user);
        return this.mapper.map(user, UserDto.class);
    } 
	
	public User getByEmail(String email) {
		User user = 	this.repository.getByEmail(email);
		return user;
	}
	
}
