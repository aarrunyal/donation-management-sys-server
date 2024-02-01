package com.donationmanagementsystem.utils;

public class AppConstant {

//	public static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
	
	public static final String PAGE_NUMBER = "0";
	
	public static final String PAGE_SIZE = "5";
	
	public static final String SORT_DIRECTION = "asc";
	
	public static final String SORT_BY = "title";
	
	public static final String  SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	public static final String TOKEN_HEADER = "Authorization";
	
	public static final String TOKEN_PREFIX = "Bearer ";
	
	public static final long ACCESS_TOKEN_VALIDITY = 240 *60 *100;
	
	public static final String[] WHITE_LIST_URL = {
			"/api/v1/auth/**"
	};
}
