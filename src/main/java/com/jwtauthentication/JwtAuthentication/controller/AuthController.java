package com.jwtauthentication.JwtAuthentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwtauthentication.JwtAuthentication.config.JwtUtil;
import com.jwtauthentication.JwtAuthentication.model.User;
import com.jwtauthentication.JwtAuthentication.model.request.LoginRequest;
import com.jwtauthentication.JwtAuthentication.model.response.ErrorResponse;
import com.jwtauthentication.JwtAuthentication.model.response.LoginResponse;

public class AuthController {

//	private final AuthenticationManager authenticationManager;
//	
//	private JwtUtil jwtUtil;
//	
//	public AuthController(AuthenticationManager authenticationManager) {
//		this.authenticationManager = authenticationManager;
//		this.jwtUtil = jwtUtil;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="login", method = RequestMethod.POST)
//	public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
//		try {
//			Authentication authentication =
//                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//            String email = authentication.getName();
//            User user = new User(email,"");
//            String token = jwtUtil.createToken(user);
//            LoginResponse loginRes = new LoginResponse(email,token);
//
//            return ResponseEntity.ok(loginRes);
//		}catch(BadCredentialsException e) {
//			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//		}catch(Exception e) {
//			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//		}
//	}
}
