package com.jwtauthentication.JwtAuthentication.controller;

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

import com.jwtauthentication.JwtAuthentication.exception.TokenMisMatchException;
import com.jwtauthentication.JwtAuthentication.model.User;
import com.jwtauthentication.JwtAuthentication.model.Dto.UserDto;
import com.jwtauthentication.JwtAuthentication.model.request.LoginRequest;
import com.jwtauthentication.JwtAuthentication.service.JwtService;
import com.jwtauthentication.JwtAuthentication.service.UserService;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder; 

@RestController
@RequestMapping(value ="/api/user") 
public class AuthController {
	
	@Autowired
	UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager; 
    
    @Autowired
    JwtService jwtService;

    @PostMapping("/create") 
    public ResponseEntity<UserDto> addNewUser(@Valid @RequestBody UserDto userInfo){ 
        UserDto userDto =  userService.addUser(userInfo); 
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    } 
  
    @GetMapping("/user") 
//    @PreAuthorize("hasAuthority('ROLE_USER')") 
    public User  getAuth(){ 
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = userService.getByEmail(auth.getName());
    	return user;
    } 
  

    
    @PostMapping(value ="/generateToken") 
    public String authenticateAndGetToken(@Valid @RequestBody LoginRequest authRequest) throws AuthenticationException{
    	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
    	 Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication.isAuthenticated()) { 
            return jwtService.generateToken(authRequest.getEmail()); 
        } else { 
            throw new UsernameNotFoundException("invalid user request !"); 
        } 
    } 
}
