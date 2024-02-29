package com.donationmanagementsystem.payload.request;

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

	@NotBlank
	@NotNull
	private String firstName;
	@NotBlank
	@NotNull
	private String lastName;
	@NotBlank
	@NotNull
	@Email
	private String email;
	@NotBlank
	@NotNull
	private String password;
}
