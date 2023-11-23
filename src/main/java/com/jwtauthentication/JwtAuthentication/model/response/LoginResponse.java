package com.jwtauthentication.JwtAuthentication.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

	private String email;
    private String token;
	public LoginResponse(String email, String token) {
		super();
		this.email = email;
		this.token = token;
	}
    
    
}
