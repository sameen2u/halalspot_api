package com.halal.sa.core;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.halal.sa.common.ServiceCommonConstants;
import com.halal.sa.common.error.ApiException;
import com.halal.sa.common.error.ErrorConstants;
import com.halal.sa.common.error.ErrorResponse;
import com.halal.sa.core.processor.ApiErrorProcessor;

public abstract class AbstractErrorProcessor implements ApiErrorProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractErrorProcessor.class.getName());
	
	@Override
	public <T> ApiResponse<T> processError(ApiRequest apiRequest, ApiException apiException) throws ApiException {

		ErrorResponse errorResponse = buildErrorResponse(apiException);
		
		if(errorResponse == null) {
			
//			LOGGER.error(ApiLoggingConstants.API_INVALID_ARGUMENTS
//					+ "Expected input parameters are null");
			throw new ApiException(
					ErrorConstants.ERR_ENCOUNTERED_DURING_PROCESSING
							.toString());
			
		} else {
			ApiResponse<T> apiResponse = formulateHttpArtifacts(apiRequest, errorResponse);
			return apiResponse;
		}
	}
	
	protected abstract ErrorResponse buildErrorResponse(ApiException apiException);
	
	protected <T> ApiResponse<T> formulateHttpArtifacts(ApiRequest apiRequest, ErrorResponse errorResponse) {
		
		ApiResponse<T> apiResponse = new ApiResponse<T>();
		apiResponse.setResponseBody((T) errorResponse);
		apiResponse.setStatusCode(errorResponse.getId());
		apiResponse.setHttpHeaders(obtainHttpHeaders());
		
		return apiResponse;
	}
	
	private HttpHeaders obtainHttpHeaders() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.put(ServiceCommonConstants.CONTENT_TYPE, Arrays.asList(ServiceCommonConstants.CONTENT_TYPE_JSON));
				
		return httpHeaders;
	}
}