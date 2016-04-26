package com.halal.sa.common.error;

public class ErrorConstants {
	
	public static final String ERRORCODE_INVALID_URL_PARAMETERS = "400";
	public static final String ERRORCODE_INTERNAL_ERROR = "500";
	public static final String ERRORCODE_AUTHENTICATION_FAILED = "401";
	public static final String ERRORCODE_INVALID_DATA = "401";
	
	public static final String ERRORCODE_INCOMPLETE_DATA = "E101";
	
	
	
	public static final String ERRORDESC_INVALID_URL_PARAMETERS = "Bad Request:Request cannot be fulfilled because of incorrect url.";
	public static final String ERRORDESC_INTERNAL_ERROR = "Internal error processing request.";
	public static final String ERRORDESC_AUTHENTICATION_FAILED = "Authentication Failed: Email and Password dont Match";
	public static final String ERRORDESC_EMAIL_ALREADY_EXIST = "Email already in Use, please use another email";
	public static final String ERRORDESC_USERID_NOT_EXIST_IN_DB = "User id is not present in the database, Cookie might have changed at client side";
	public static final String ERRORDESC_INCOMPLETE_DATA = "Incomplete data provided, please complete the form and resubmit it.";
}
