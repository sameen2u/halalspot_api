package com.halal.sa.core.exception;

public class ErrorConstants {
	
	public static final String ERRORDESC_EMAIL_NOT_FOUND = "Email not registered. Please signup.";
	public static final String INVALID_PATH_PARAMETER = "This request could not be fulfulled because of an invalid path parameter.";
	public static final String ERRORDESC_RULE_NOT_FOUND = "Rule Not Found.";
	public static final String ERRORDESC_LOGIN_EMAIL_OR_PASSWORD_MISSING = "Email or Password is empty please resubmit the request";
	public static final String ERRORDESC_RECORD_NOT_FOUND = "Record Not Found.";
	public static final String ERRORDESC_EMPTY_SEARCH_RESULT = "No data found for search.";
	public static final String ERRORDESC_UNAUTHORIZED = "Unauthorized to access the service.";
	public static final String ERRORDESC_INCORRECT_HTTP_METHOD = "Incorrect HTTP Method.";
	public static final String ERRORDESC_UNSUPPORTED_MEDIA_TYPE = "Unsupported Media Type";
	public static final String ERRORDESC_INTERNAL_ERROR_CODE= "ERRORDESC_INTERNAL_ERROR";
	public static final String ERRORDESC_SERVICE_UNAVAILABLE = "Service unavailable";
	public static final String ERRORDESC_INVALID_REQUEST_PARAMETERS = "Bad Request:Request cannot be fulfilled because of incorrect input.";
	
	public static final String VAL_INVALID_URL_PARAMETERS = "VAL_INVALID_URL_PARAMETERS";
	public static final String VAL_ERR_EMPTY_PARAMS_CODE = "VAL_ERR_EMPTY_PARAMS";
	public static final String VAL_ERR_EMPTY_PARAMS_MESSAGE = "No parameters found";
	public static final String VAL_ERR_REPEATED_PARAMS_CODE = "VAL_ERR_REPEATED_PARAMS";
	public static final String VAL_ERR_REPEATED_PARAMS_MESSAGE = "Parameter=''{0}'' repeated=''{1}'' times";
	public static final String VAL_ERR_IS_ONE_OF_THE_MANDATORY_MESSAGE = "Atleast one of the mandatory parameters ''{0}'' or ''{1}''should be present";
	
	public static final String INVALID_FILTER_OPTIONS = "Invalid Filter options";
	
	public static final String ERRORCODE_INVALID_DATA = "INVALID_DATA";
	public static final String ERRORCODE_AUTHENTICATION_FAILED = "AUTHENTICATION_FAILED";

	//Halal specific
	public static final String LOGIN_ERR_100 = "Login authentication Failed, invalid email and password combination";
	public static final String ERRORDESC_INVALID_URL_PARAMETERS = "Bad Request:Request cannot be fulfilled because of incorrect url.";
	public static final String ERRORDESC_INTERNAL_ERROR = "Internal error processing request.";
	public static final String ERRORDESC_AUTHENTICATION_FAILED = "Authentication Failed: Email and Password dont Match";
	public static final String ERRORDESC_EMAIL_ALREADY_EXIST = "Email already in Use, please use another email";
	public static final String ERRORDESC_USERID_NOT_EXIST_IN_DB = "User id is not present in the database, Cookie might have changed at client side";
	public static final String ERRORDESC_INCOMPLETE_DATA = "Incomplete data provided, please complete the form and resubmit it.";
	public static final String ERR_ENCOUNTERED_DURING_PROCESSING = "Error encountered before execution of processors";
	public static final String ERRORDESC_MONGODB_UNAVAILABLE = "No response from MongoDB, please check the logs for more details.";
	
	public static final String DYNAMIC_ERRORDESC_INVALID_REQUEST_PARAMETERS = "Bad Request:Request cannot be fulfilled because of incorrect input for itemId={0}";
	public static final String DYNAMIC_ERRORDESC_INVALID_URL_PARAMETERS = "Bad Request:Request cannot be fulfilled because of incorrect url for itemId={0}";
	public static final String DYNAMIC_ERRORDESC_RECORD_NOT_FOUND = "Record Not Found for itemId={0}";
	public static final String ERROR_FBT_IDS_NOT_FOUND="No FBT ids found for the itemId {0}";
	public static final String ERROR_REQUIRED_MESSAGE = "Required ''{0}''({1}), if  ''{2}'' is ''{3}''";


	
}
