package com.jwtauthentication.JwtAuthentication.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauthentication.JwtAuthentication.config.JwtUtil;
import com.jwtauthentication.JwtAuthentication.model.User;
import com.jwtauthentication.JwtAuthentication.model.request.LoginRequest;
import com.jwtauthentication.JwtAuthentication.model.response.ErrorResponse;
import com.jwtauthentication.JwtAuthentication.model.response.LoginResponse;
import com.jwtauthentication.JwtAuthentication.service.JwtService;
import com.jwtauthentication.JwtAuthentication.service.UserInfoService;
@RestController
@RequestMapping("/auth") 
public class AuthController {

    @Autowired
    private UserInfoService userInfoService; 
  
    @Autowired
    private JwtService jwtService; 
  
    @Autowired
    private AuthenticationManager authenticationManager; 
  
    @GetMapping("/welcome") 
    public String welcome() { 
        return "Welcome this endpoint is not secure"; 
    } 
  
//    @PostMapping("/addNewUser") 
//    public String addNewUser(@RequestBody User userInfo) { 
//        return userInfoService.addUser(userInfo); 
//    } 
  
    @GetMapping("/user/userProfile") 
    @PreAuthorize("hasAuthority('ROLE_USER')") 
    public String userProfile() { 
        return "Welcome to User Profile"; 
    } 
  
    @GetMapping("/admin/adminProfile") 
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public String adminProfile() { 
        return "Welcome to Admin Profile"; 
    } 
  
    @PostMapping("/generateToken") 
    public String authenticateAndGetToken(@RequestBody LoginRequest authRequest) { 
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())); 
        if (authentication.isAuthenticated()) { 
            return jwtService.generateToken(authRequest.getEmail()); 
        } else { 
            throw new UsernameNotFoundException("invalid user request !"); 
        } 
    } 
}
