package com.donationmanagementsystem.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppConstant {

	// public static final String SECRET_KEY =
	// "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

	public static final String PAGE_NUMBER = "0";

	public static final String PAGE_SIZE = "5";

	public static final String SORT_DIRECTION = "asc";

	public static final String SORT_BY = "title";

	public static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	public static final String TOKEN_HEADER = "Authorization";

	public static final String TOKEN_PREFIX = "Bearer ";

	public static final long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 24;

	public static final String[] WHITE_LIST_URL = {
			"/api/v1/auth/**",
			"/api/test/**",
			"/uploads/**"
	};

	public final static String INTERNAL_SERVER_ERROR = "Internal Server Error";

	public final static String NO_CONTETNT_FOUND = "No content found";

	public final static String CREATED = "Request processed successfully";

	public final static String OK = "Request processed successfully";

	public final static String NOT_ACCEPTABLE = "Request not acceptable";

	public final static int THUMBNAIL_WIDTH = 200;

	public final static int THUMBNAIL_LENGTH = 200;

	public static final String UPLOAD_ROOT_PATH = "uploads";

	public static final List<String> IMAGE_EXTENSION = new ArrayList(Arrays.asList("png", "jpg", "jpeg"));
}
