package com.donationmanagementsystem.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserResponse;
import com.donationmanagementsystem.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService service;

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(
			@RequestBody RegisterRequest request
			){
		return service.register(request);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest  request
			){
		return ResponseEntity.ok(service.authenticate(request));
	}

	@GetMapping("/user")
	public ResponseEntity<UserResponse> getLoggedInUser() {
		User user = userService.getLoggedInUser();
		var userResponse = UserResponse
		.builder()
		.firstName(user.getFirstName())
		.lastName(user.getLastName())
		.id(user.getId())
		.email(user.getEmail()).build();
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<ApiResponse> verifyUser(@PathVariable String token){
		return service.verifyUser(token);
	}
	


	
}
