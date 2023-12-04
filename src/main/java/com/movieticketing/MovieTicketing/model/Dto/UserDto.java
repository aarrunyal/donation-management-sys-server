package com.movieticketing.MovieTicketing.model.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	
	
	
}
