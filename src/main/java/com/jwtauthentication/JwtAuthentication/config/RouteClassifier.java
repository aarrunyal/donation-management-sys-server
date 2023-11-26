package com.jwtauthentication.JwtAuthentication.config;

public class RouteClassifier {

	public static final String[] SECURED_URLS= {"/posts/**", "/comments/**", "/category/**"};
	
	public static final String[] UN_SECURED_URLS= {"/api/users/create"};

}
