package com.halal.sa.common.error;

import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.processor.ApiErrorProcessor;

public class ApiErrorProcessorImpl implements ApiErrorProcessor{

	public Object processError(ApiRequest apiRequest, ApiException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(),exception.getErrorMessage());
		return errorResponse;	
	}

}
