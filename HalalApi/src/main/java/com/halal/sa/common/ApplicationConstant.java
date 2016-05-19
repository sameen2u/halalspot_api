package com.halal.sa.common;

import java.util.ResourceBundle;

public class ApplicationConstant {
	
	public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("Application");
	
	public static final String GOOGLE_GEO_API_ENDPOINT = resourceBundle.getString("GOOGLE_GEO_API_ENDPOINT");
	
	public static final int BUSINESS_RECORDS_PER_PAGE = Integer.parseInt(resourceBundle.getString("BUSINESS_RECORDS_PER_PAGE"));
	public static final int BUSINESS_PAGINATION_EXTRA_RECORD = Integer.parseInt(resourceBundle.getString("BUSINESS_PAGINATION_EXTRA_RECORD"));
	

}
