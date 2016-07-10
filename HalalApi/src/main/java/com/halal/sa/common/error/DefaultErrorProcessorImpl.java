package com.halal.sa.common.error;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.halal.sa.core.AbstractErrorProcessor;

@Component
public class DefaultErrorProcessorImpl extends AbstractErrorProcessor {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultErrorProcessorImpl.class.getName());
	private static final DefaultErrorProcessorImpl INSTANCE = new DefaultErrorProcessorImpl();
	
	/**
	 * Private constructor enforces no instantiation of this class
	 */
	protected DefaultErrorProcessorImpl() {
		
	}
	
	/**
	 * Method returns a singleton instance of this class
	 * @return DefaultErrorProcessorImpl
	 */
	public static DefaultErrorProcessorImpl getInstance() {
		
		return INSTANCE;
	}
	
	private static final ErrorResponse invalidPathParamater = new ErrorResponse(
			ErrorConstants.ERRORCODE_INVALID_URL_PARAMETERS,
			ErrorConstants.INVALID_PATH_PARAMETER);
	
	private static final ErrorResponse badRequestError = new ErrorResponse(
			ErrorConstants.ERRORCODE_INVALID_URL_PARAMETERS,
			ErrorConstants.ERRORDESC_INVALID_REQUEST_PARAMETERS);

	private static final ErrorResponse unauthorizedError = new ErrorResponse(
			ErrorConstants.ERRORCODE_UNAUTHORIZED,
			ErrorConstants.ERRORDESC_UNAUTHORIZED);

	private static final ErrorResponse recordNotFoundError = new ErrorResponse(
			ErrorConstants.ERRORCODE_RECORD_NOT_FOUND,
			ErrorConstants.ERRORDESC_RECORD_NOT_FOUND);

	private static final ErrorResponse internalServerError = new ErrorResponse(
			ErrorConstants.ERRORCODE_INTERNAL_ERROR,
			ErrorConstants.ERRORDESC_INTERNAL_ERROR);

	private static final ErrorResponse serviceUnavailableError = new ErrorResponse(
			ErrorConstants.ERRORCODE_SERVICE_UNAVAILABLE,
			ErrorConstants.ERRORDESC_SERVICE_UNAVAILABLE);
	
	private static final ErrorResponse methodNotAllowedError = new ErrorResponse(
			ErrorConstants.ERRORCODE_INCORRECT_HTTP_METHOD,
			ErrorConstants.ERRORDESC_INCORRECT_HTTP_METHOD);
	
	private static final ErrorResponse gatewayTimeoutError = new ErrorResponse(
			ErrorConstants.ERRORCODE_GATEWAY_TIMEOUT,
			ErrorConstants.ERRORDESC_GATEWAY_TIMEOUT);
	
	private static final ErrorResponse loginEmailOrPasswordMissingError = new ErrorResponse(
			ErrorConstants.ERRORCODE_LOGIN_EMAIL_OR_PASSWORD_MISSING,
			ErrorConstants.ERRORDESC_LOGIN_EMAIL_OR_PASSWORD_MISSING);
	
	private static final ErrorResponse requestCouldNotBeProcessedError = new ErrorResponse(
			ErrorConstants.ERRORCODE_REQUEST_COULD_NOT_BE_PROCESSED,
			ErrorConstants.ERRORDESC_REQUEST_COULD_NOT_BE_PROCESSED);
	
		
	private static final Map<String, ErrorResponse> errorCodeToErrorResp = populateErrorCodetoErrorRespMap();
	
	private static Map<String, ErrorResponse> populateErrorCodetoErrorRespMap() {
		
		Map<String, ErrorResponse> errorCodeToErrorRespMap = new HashMap<String, ErrorResponse>();
		
		errorCodeToErrorRespMap.put(ErrorCode.ERR_ENCOUNTERED_DURING_PROCESSING.toString(), internalServerError);
		errorCodeToErrorRespMap.put(ErrorCode.ERR_COMMUNICATING_WITH_EXTERNAL_SYSTEM.toString(), serviceUnavailableError);
		errorCodeToErrorRespMap.put(ErrorCode.ERR_BAD_REQUEST.toString(), badRequestError);
//		errorCodeToErrorRespMap.put(SpecificErrorCode.INVALID_PATH_PARAMETER.toString(), invalidPathParamater);
		errorCodeToErrorRespMap.put(ErrorCode.ERR_RECORD_NOT_FOUND.toString(), recordNotFoundError);
		errorCodeToErrorRespMap.put(ErrorCode.ERR_UNAUTHORIZED.toString(), unauthorizedError);
		errorCodeToErrorRespMap.put(ErrorCode.ERR_SERVICE_UNAVAILABLE.toString(), serviceUnavailableError);
		errorCodeToErrorRespMap.put(ErrorCode.ERR_METHOD_NOT_ALLOWED.toString(), methodNotAllowedError);
		errorCodeToErrorRespMap.put(ErrorCode.ERR_GATEWAY_TIMEOUT.toString(), gatewayTimeoutError);
		errorCodeToErrorRespMap.put(ErrorCode.ERRORCODE_LOGIN_EMAIL_OR_PASSWORD_MISSING.toString(), loginEmailOrPasswordMissingError);
		errorCodeToErrorRespMap.put(ErrorCode.ERR_REQUEST_COULD_NOT_BE_PROCESSED.toString(), requestCouldNotBeProcessedError);
		errorCodeToErrorRespMap.put(ErrorCode.ERRORCODE_EMAIL_NOT_FOUND.toString(), unauthorizedError);
		errorCodeToErrorRespMap.put(ErrorCode.ERR_MONGODB_UNAVAILABLE.toString(), serviceUnavailableError);
		
		
		return errorCodeToErrorRespMap;
	}
	
	@Override
	public ErrorResponse buildErrorResponse(ApiException apiException) {
		
		return getErrorResponse(apiException.getErrorCode(), apiException.getErrorMessage());
	}

	private ErrorResponse getErrorResponse(String errorCode, String errorMessage) {
		
		ErrorResponse errorResponse = null;
		
		if(StringUtils.isNotBlank(errorCode) && StringUtils.isBlank(errorMessage)) {
			
			if(errorCodeToErrorResp.containsKey(errorCode)) {
				
				errorResponse = errorCodeToErrorResp.get(errorCode);
			} else {
				
//				LOGGER.error(ApiLoggingConstants.API_ERRORCODE_NOT_PRESENT + "ErrorCode: " + errorCode);
				errorResponse = internalServerError;
			}
			
		} else {
			if(errorCodeToErrorResp.containsKey(errorCode)) {
				
				ErrorResponse errorResponseObj = errorCodeToErrorResp.get(errorCode);
				errorResponse = new ErrorResponse(errorResponseObj.getId(), errorMessage);
			} else {
				
//				LOGGER.error(ApiLoggingConstants.API_ERRORCODE_NOT_PRESENT + "ErrorCode: " + errorCode + " : ErrorMessage: " + errorMessage);
				errorResponse = new ErrorResponse(badRequestError.getId(), errorMessage);
			}
		}
		
		return errorResponse;
	}
	
	public ResponseEntity<ErrorResponse> getErrorResponse(ErrorResponse errorResponseObj){
		
		if(errorResponseObj == null){
			errorResponseObj = new ErrorResponse(
					ErrorConstants.ERRORCODE_INTERNAL_ERROR,
					ErrorConstants.ERRORDESC_INTERNAL_ERROR);
		}
			
		 
		 ResponseEntity<ErrorResponse> errorResponse= new ResponseEntity<>(errorResponseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		return errorResponse;
		
	}
	

}
