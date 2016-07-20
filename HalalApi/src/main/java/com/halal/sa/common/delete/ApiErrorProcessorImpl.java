package com.halal.sa.common.delete;

import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.ApiResponse;
import com.halal.sa.core.apiprocessor.ApiErrorProcessor;
import com.halal.sa.core.exception.ApiException;

public class ApiErrorProcessorImpl implements ApiErrorProcessor{

	@Override
	public <T> ApiResponse<T> processError(ApiRequest apiRequest,
			ApiException apiException) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

//	public Object processError(ApiRequest apiRequest, ApiException exception) {
//		ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(),exception.getErrorMessage());
//		return errorResponse;	
//	}

}
