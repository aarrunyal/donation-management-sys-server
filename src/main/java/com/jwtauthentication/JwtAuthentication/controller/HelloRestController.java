package com.jwtauthentication.JwtAuthentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//https://www.toptal.com/spring/spring-security-tutorial
//https://medium.com/code-with-farhan/spring-security-jwt-authentication-authorization-a2c6860be3cf

@RestController
@RequestMapping("hello")
public class HelloRestController {

	
	@GetMapping("user")
	public String helloUser() {
		return "Hello User";
	}
	
	@GetMapping("admin")
	public String helloAdmin() {
		return "Hello Admin";
	}
}
