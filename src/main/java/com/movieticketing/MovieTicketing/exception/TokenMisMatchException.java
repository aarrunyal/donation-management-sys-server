package com.movieticketing.MovieTicketing.exception;

public class TokenMisMatchException extends RuntimeException {


	
	public TokenMisMatchException() {
		super(String.format("Invalid Token"));
	}
}
