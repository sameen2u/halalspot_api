package com.halal.sa.core.apiprocessor;

import java.util.Map;

import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.ApiResponse;
import com.halal.sa.core.exception.ApiException;


public interface ApiErrorProcessor {

	public <T> ApiResponse<T> processError(final ApiRequest apiRequest, final ApiException 
			apiException) throws ApiException;
	
}
