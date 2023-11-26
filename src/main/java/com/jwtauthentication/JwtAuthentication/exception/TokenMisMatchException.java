package com.jwtauthentication.JwtAuthentication.exception;

public class TokenMisMatchException extends RuntimeException {


	
	public TokenMisMatchException() {
		super(String.format("Invalid Token"));
	}
}
