package com.halal.sa.common;

import java.util.ResourceBundle;

public class ApiConstant {
	
	public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("Productinfo");
	
	public static final String API_HOST = resourceBundle.getString("API_HOST");
	public static final String API_PORT = resourceBundle.getString("API_PORT");
	
	public static final String API_TOKEN_TYPE_SESSION = "session";
	public static final String API_TOKEN_TYPE_ACTIVITY = "activity";
	
	public static final int SEARCH_KEYWORD_AUTO_POPULATE_LIMIT = 5;
}
