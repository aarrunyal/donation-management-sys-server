package com.jwtauthentication.JwtAuthentication.model.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {
	
	@NotEmpty
	@Email(message="Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min=8, message = "Password should be 8 digit long")
	private String password;
	
	@NotEmpty(message="First cannot be empty")
	@Size(min=4, message = "First name cannot be empty")
	private String firstName;
	
	@NotEmpty(message="Last cannot be empty")
	@Size(min=4, message = "Last name cannot be empty")
	private String lastName;
	
	
	private String roles;
	
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
	
}
