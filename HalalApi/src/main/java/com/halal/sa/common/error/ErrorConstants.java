package com.halal.sa.common.error;

public class ErrorConstants {
	
	public static final int ERRORCODE_RECORD_NOT_FOUND = 404;
	public static final int ERRORCODE_EMPTY_SEARCH_RESULT = 404;
	public static final int ERRORCODE_FETCH_TEMPLATE_UNAVAILABLE = 503;
	public static final int ERRORCODE_INVALID_URL_PARAMETERS = 400;
	public static final int ERRORCODE_INCORRECT_HTTP_METHOD = 405;
	public static final int ERRORCODE_UNSUPPORTED_MEDIA_TYPE = 415;
	public static final int ERRORCODE_INTERNAL_ERROR = 500;
	public static final int ERRORCODE_SERVICE_UNAVAILABLE = 503;
	public static final int ERRORCODE_UNAUTHORIZED = 401;
	public static final int ERRORCODE_GATEWAY_TIMEOUT = 504;
	public static final int ERRORCODE_REQUEST_COULD_NOT_BE_PROCESSED = 500;
	public static final int ERRORCODE_RULE_NOT_FOUND = 404;
	public static final int ENDECA_ERROR_RULE_MISSING = -6;	
	
	public static final String INVALID_PATH_PARAMETER = "This request could not be fulfulled because of an invalid path parameter.";
	public static final String ERRORDESC_RULE_NOT_FOUND = "Rule Not Found.";
	public static final String ERRORDESC_FETCH_TEMPLATE_UNAVAILABLE = "Fetch Template is not available in Content Grid.";
	public static final String ERRORDESC_RECORD_NOT_FOUND = "Record Not Found.";
	public static final String ERRORDESC_EMPTY_SEARCH_RESULT = "No data found for search.";
	public static final String ERRORDESC_UNAUTHORIZED = "Unauthorized to access the service.";
	public static final String ERRORDESC_INCORRECT_HTTP_METHOD = "Incorrect HTTP Method.";
	public static final String ERRORDESC_UNSUPPORTED_MEDIA_TYPE = "Unsupported Media Type";
	public static final String ERRORDESC_INTERNAL_ERROR_CODE= "ERRORDESC_INTERNAL_ERROR";
	public static final String ERRORDESC_SERVICE_UNAVAILABLE = "Service unavailable";
	public static final String ERRORDESC_INVALID_REQUEST_PARAMETERS = "Bad Request:Request cannot be fulfilled because of incorrect input.";
	public static final String ERRORDESC_GATEWAY_TIMEOUT = "Gateway timeout.";
	public static final String ERRORDESC_REQUEST_COULD_NOT_BE_PROCESSED = "This request could not be processed.";
	public static final String ERRORDESC_ESTABLISH_CONN ="Error establishing connection";
	public static final String ERROR_MSG_RECORD_NOT_FOUND="Navigation Engine not able to process request";
	public static final String PROD_SRCH_ERR_500_CODE = "PROD_SRCH_ERR_500";
	public static final String PROD_SRCH_ERR_500_MESSAGE = "Error encountered during processing";
	public static final String ERRORDESC_INVALID_MAX_PRODUCT = "Incorrect Max Products";
	public static final String ERRORDESC_INVALID_STOREID ="StoreID is not valid";
	public static final String ERRORDESC_INVALID_KEYWORD="Incorrect KeyWord";
	public static final String ERRORDESC_CROSSED_MAX_PRODUCT_LIMIT="Max product limit-16 has been crossed";
	public static final String ERRORDESC_UNIQUE_ADDRESS_NOT_FOUND = "Unique address could not be found for parameters provided.";
	
	public static final String VAL_INVALID_URL_PARAMETERS = "VAL_INVALID_URL_PARAMETERS";
	public static final String VAL_ERR_EMPTY_PARAMS_CODE = "VAL_ERR_EMPTY_PARAMS";
	public static final String VAL_ERR_EMPTY_PARAMS_MESSAGE = "No parameters found";
	public static final String VAL_ERR_REPEATED_PARAMS_CODE = "VAL_ERR_REPEATED_PARAMS";
	public static final String VAL_ERR_REPEATED_PARAMS_MESSAGE = "Parameter=''{0}'' repeated=''{1}'' times";
	public static final String VAL_ERR_IS_ONE_OF_THE_MANDATORY_MESSAGE = "Atleast one of the mandatory parameters ''{0}'' or ''{1}''should be present";
	public static final String VAL_ERR_PARAMETER_NAMES_CODE = "VAL_ERR_PARAMETER_NAMES";
	public static final String VAL_ERR_PARAMETER_NAMES_MESSAGE = "Request URL contains paramters not accepted by the service=''{0}''";
	public static final String VAL_ERR_PARAMETER_COUNT_CODE = "VAL_ERR_PARAMETER_COUNT";
	public static final String VAL_ERR_PARAMETER_COUNT_MESSAGE = "Maximum parameter count exceeding for ''{0}''";
	public static final String VAL_ERR_ACCEPTED_PARAM_VALUES_CODE = "VAL_ERR_ACCEPTED_PARAM_VALUES";
	public static final String VAL_ERR_ACCEPTED_PARAM_VALUES_MESSAGE = "Invalid value for parameter ''{0}''";
	public static final String VAL_ERR_INVALID_PARAM_VALUES_CODE = "VAL_ERR_INVALID_PARAM_VALUES";
	public static final String VAL_ERR_INVALID_PARAM_VALUES_MESSAGE = "Invalid value for parameter ''{0}''";
	public static final String VAL_ERR_MIN_MAX_LENGTH_CODE = "VAL_ERR_MIN_MAX_LENGTH";
	public static final String VAL_ERR_MIN_MAX_LENGTH_MESSAGE = "Length exceeding for parameter ''{0}''";
	public static final String VAL_ERR_MIN_LENGTH_MESSAGE = "Minimum value length not met for parameter ''{0}''.";
	public static final String VAL_ERR_MAX_LENGTH_MESSAGE = "Maximum value length exceeded for parameter ''{0}''.";
	public static final String VAL_ERR_DATA_TYPE_CODE = "VAL_ERR_DATA_TYPE";
	public static final String VAL_ERR_DATA_TYPE_MESSAGE = "Datatype mismatch for parameter ''{0}''";
	public static final String VAL_ERR_EMPTY_PARAM_VALUES_CODE = "VAL_ERR_EMPTY_PARAM_VALUES";
	public static final String VAL_ERR_EMPTY_PARAM_VALUES_MESSAGE = "Parameter value empty for ''{0}''";
	public static final String VAL_ERR_IS_FILLED_CODE = "VAL_ERR_IS_FILLED";
	public static final String VAL_ERR_IS_FILLED_MESSAGE = "Parameter value empty for ''{0}''";
	public static final String VAL_ERR_IS_MANDATORY_CODE = "VAL_ERR_IS_MANDATORY";
	public static final String VAL_ERR_IS_MANDATORY_MESSAGE = "Atleast one of the mandatory parameters ''{0}'' should be present";
	public static final String VAL_ERR_IS_IN_RANGE_CODE = "VAL_ERR_IS_IN_RANGE";
	public static final String VAL_ERR_IS_IN_RANGE_MESSAGE = "Parameter value ''{0}'' not within range";
	public static final String VAL_ERR_OVERLAPPING_PARAMETERS_CODE = "VAL_ERR_OVERLAPPING_PARAMETERS";
	public static final String VAL_ERR_OVERLAPPING_PARAMETERS_MESSAGE = "Overlapping input parameters";
	public static final String VAL_ERR_IS_MUTUAL_DEPENDENT_PARAMETERS_MESSAGE = "Required ''{0}'' and ''{1}'' values as its mutual dependent";
	public static final String VAL_ERR_LOWER_BOUND_GREATER_UPPER_BOUND_MESSAGE = "''{0}'' ''{1}'' value should always be  less than ''{2}'' ''{3}''" ;
	public static final String VAL_ERR_AMBIGUOUS_OPERATION_CODE = "VAL_ERR_AMBIGUOUS_OPERATION";
	public static final String VAL_ERR_AMBIGUOUS_OPERATION_MESSAGE = "The provided parameters does not resolve to an unique operation.";
	public static final String VAL_ERR_INVALID_OPERATION_CODE = "VAL_ERR_INVALID_OPERATION";
	public static final String VAL_ERR_INVALID_OPERATION_MESSAGE = "The provided parameters does not resolve to any operation.";
	public static final String ERR_V2_STORESEARCH_AMBIGUOUS_ADDRESS = "ERR_V2_STORESEARCH_AMBIGUOUS_ADDRESS";
	public static final String VAL_ERR_RANGE_FILTER_MANDATORY_MESSAGE = "''{0}'' and ''{1}'' values are mandatory if we have ''{2}'' parameter in request";
	public static final String VAL_ERR_INVALID_NAVPARAM = " Invalid Navparam "; 
	
	public static final String INVALID_FILTER_OPTIONS = "Invalid Filter options";
	
	public static final String VAL_PASSED_VALUE_NOT_ALLOWED = "Parameter=''{0}'' passed value=''{1}'' is not allowed";
	
	// API : 3444 - constants for the dynamic error response
	public static final String DYNAMIC_ERRORDESC_INVALID_REQUEST_PARAMETERS = "Bad Request:Request cannot be fulfilled because of incorrect input for itemId={0}";
	public static final String DYNAMIC_ERRORDESC_INVALID_URL_PARAMETERS = "Bad Request:Request cannot be fulfilled because of incorrect url for itemId={0}";
	public static final String DYNAMIC_ERRORDESC_RECORD_NOT_FOUND = "Record Not Found for itemId={0}";
	public static final String ERROR_FBT_IDS_NOT_FOUND="No FBT ids found for the itemId {0}";
	public static final String ERROR_REQUIRED_MESSAGE = "Required ''{0}''({1}), if  ''{2}'' is ''{3}''";


	public static final String ERRORCODE_INVALID_DATA = "INVALID_DATA";
	public static final String ERRORCODE_AUTHENTICATION_FAILED = "AUTHENTICATION_FAILED";
	public static final String ERRORCODE_INCOMPLETE_DATA = "E101";
	
	
	public static final String API_ERR_100 = "Login authentication Failed, invalid email and password combination";
	public static final String ERRORDESC_INVALID_URL_PARAMETERS = "Bad Request:Request cannot be fulfilled because of incorrect url.";
	public static final String ERRORDESC_INTERNAL_ERROR = "Internal error processing request.";
	public static final String ERRORDESC_AUTHENTICATION_FAILED = "Authentication Failed: Email and Password dont Match";
	public static final String ERRORDESC_EMAIL_ALREADY_EXIST = "Email already in Use, please use another email";
	public static final String ERRORDESC_USERID_NOT_EXIST_IN_DB = "User id is not present in the database, Cookie might have changed at client side";
	public static final String ERRORDESC_INCOMPLETE_DATA = "Incomplete data provided, please complete the form and resubmit it.";
	public static final String ERR_ENCOUNTERED_DURING_PROCESSING = "Error encountered before execution of processors";
	
}
