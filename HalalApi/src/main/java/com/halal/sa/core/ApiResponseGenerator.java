package com.halal.sa.core;

import org.springframework.http.ResponseEntity;

import com.halal.sa.common.error.ApiException;

public interface ApiResponseGenerator {
	
	public abstract <T> ResponseEntity<T> generateResponse(ApiRequest apiRequest, ApiResponse<T> apiResponse) throws ApiException;


}
