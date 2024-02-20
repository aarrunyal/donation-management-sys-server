package com.donationmanagementsystem.payload.response;

import com.donationmanagementsystem.config.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
	public Long id;
	public String firstName;
	public String lastName;
	public String email;
	public Role role;
}
