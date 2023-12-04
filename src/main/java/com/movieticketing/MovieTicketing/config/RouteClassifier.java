package com.movieticketing.MovieTicketing.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RouteClassifier {

	public static final String[] SECURED_URLS= {"/api/user/auth", "/api/category/**", "/api/movie/**", "/api/theater/**"};
	
	public static final String[] UN_SECURED_URLS= {"/api/user/create", "/api/user/generateToken"};
	
	
	public static  List<String> getSecuredUrls() {
		return Arrays.asList(SECURED_URLS);
	}
	
	public static  List<String>  getUnSecuredUrls() {
		return Arrays.asList(UN_SECURED_URLS);
	}

}
