package com.halal.sa.core;

import java.io.NotSerializableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.ErrorConstants;

/**
 * Class is responsible for producing a response based on the input 
 * parameters. 
 * @param <E>
 * @param <E>
 */
@Component
public final class ApiResponseGeneratorImpl implements ApiResponseGenerator {
	
	static final Logger LOGGER = LoggerFactory.getLogger(ApiResponseGeneratorImpl.class.getName());
	
	
	/**
	 * Private constructor enforces no instantiation of this class
	 */
	private ApiResponseGeneratorImpl() {
		
	}
	

	/**
	 * This method sets the status code, body, headers in the HTTP Response object.
	 * @param <T>
	 * @throws NotSerializableException 
	 */
	@Override
	public <T> ResponseEntity<T> generateResponse(ApiRequest apiRequest, ApiResponse<T> apiResponse) throws ApiException {
		ResponseEntity<T> response=null;
		if (apiRequest == null || apiResponse == null
				|| apiResponse.getStatusCode() == 0
				|| apiResponse.getHttpHeaders() == null
				|| apiResponse.getHttpHeaders().isEmpty()
				|| apiResponse.getResponseBody() == null) {

//			LOGGER.error(ApiLoggingConstants.API_INVALID_ARGUMENTS + "Expected input parameters are null");
			throw new ApiException(ErrorConstants.ERR_ENCOUNTERED_DURING_PROCESSING.toString());
		} else {
			
			response=getResponse(apiResponse.getResponseBody(),apiResponse.getHttpHeaders(),apiResponse.getStatusCode());			
				
				
		}
		return response;
	}
	
	private <T> ResponseEntity<T> getResponse(T t,
			HttpHeaders httpHeaders, int status) {
		
		ResponseEntity<T> entity=new ResponseEntity<T>(t, httpHeaders, HttpStatus.valueOf(status));
		
		return entity;
	}


	

}
