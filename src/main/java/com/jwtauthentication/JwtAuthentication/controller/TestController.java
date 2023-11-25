package com.jwtauthentication.JwtAuthentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/check")
	public String testFunc() 
	{
		System.out.println("Hello");
		return "Welcome";
	}
}
