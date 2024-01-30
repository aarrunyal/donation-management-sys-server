package com.donationmanagementsystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.exception.TokenMisMatchException;
import com.donationmanagementsystem.model.User;
import com.donationmanagementsystem.model.Dto.CategoryDto;
import com.donationmanagementsystem.model.Dto.UserDto;
import com.donationmanagementsystem.model.request.LoginRequest;
import com.donationmanagementsystem.service.JwtService;
import com.donationmanagementsystem.service.UserService;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder; 

@RestController
@RequestMapping(value ="/api") 
public class AuthController {
	
	@Autowired
	UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager; 
    
    @Autowired
    JwtService jwtService;

    @PostMapping("/create") 
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public ResponseEntity<UserDto> addNewUser(@Valid @RequestBody UserDto userInfo) throws ResourceNotFoundException{ 
        UserDto userDto =  userService.addUser(userInfo); 
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    } 
  
    @GetMapping("/auth") 
    public ResponseEntity<UserDto>  getAuth(){ 
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	UserDto user = userService.getByEmail(auth.getName());
    	return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    } 
  

    
    @PostMapping(value ="/login") 
    public Map<String, String> authenticateAndGetToken(@Valid @RequestBody LoginRequest authRequest) throws AuthenticationException{
    	System.out.println(authRequest.getEmail());
    	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
    	 Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication.isAuthenticated()) { 
        	UserDto user = userService.getByEmail(authentication.getName());
        	Map<String, String> auth = new HashMap<>();
        	auth.put("token", jwtService.generateToken(authRequest.getEmail()));
        	auth.put("first_name", user.getFirstName());
        	auth.put("last_name", user.getLastName());
        	return auth;
        } else { 
            throw new UsernameNotFoundException("invalid user request !"); 
        } 
    } 
}
