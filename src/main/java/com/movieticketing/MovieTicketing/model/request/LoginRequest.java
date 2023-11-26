package com.movieticketing.MovieTicketing.model.request;

import jakarta.validation.constraints.NotEmpty;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class LoginRequest {

	@NotEmpty(message="Email cannot be empty")
	private String email;
	
	@NotEmpty(message="Password cannot be epmpty")
	private String password;

	public LoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
