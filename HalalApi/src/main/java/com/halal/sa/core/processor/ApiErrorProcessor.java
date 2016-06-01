package com.halal.sa.core.processor;

import java.util.Map;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.ApiResponse;


public interface ApiErrorProcessor {

	public <T> ApiResponse<T> processError(final ApiRequest apiRequest, final ApiException 
			apiException) throws ApiException;
	
}
