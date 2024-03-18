package com.donationmanagementsystem.payload.request;

import com.donationmanagementsystem.config.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	@NotBlank(message = "First name is blank")
	@NotNull(message = "First name is empty")
	private String firstName;

	@NotBlank(message = "Last name is empty")
	@NotNull(message = "Last name is empty")
	private String lastName;

	@NotBlank(message = "Email is empty")
	@NotNull(message = "Email is empty")
	@Email(message = "Not a valid email")
	private String email;

	// @NotBlank(message="Password cannot be blank")
	// @NotNull(message="Password cannot be empty")
	private String password;

	@NotNull(message = "Role cannot be empty")
	private  Role role;

}
