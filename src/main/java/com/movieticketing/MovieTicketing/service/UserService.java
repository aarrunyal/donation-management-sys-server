package com.movieticketing.MovieTicketing.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movieticketing.MovieTicketing.config.UserInfoDetails;
import com.movieticketing.MovieTicketing.exception.ResourceAlreadyExistException;
import com.movieticketing.MovieTicketing.exception.ResourceNotFoundException;
import com.movieticketing.MovieTicketing.model.User;
import com.movieticketing.MovieTicketing.model.Dto.UserDto;
import com.movieticketing.MovieTicketing.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetailsService;

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
		if(userInfo.getRoles().equals(null)) {
			user.setRoles("ROLE_USER");
		}
        user = this.repository.save(user);
        return this.mapper.map(user, UserDto.class);
    } 
	
	public UserDto getByEmail(String email) {
		User user = 	this.repository.getByEmail(email);
		return this.mapper.map(user,UserDto.class);
	}
	
}
