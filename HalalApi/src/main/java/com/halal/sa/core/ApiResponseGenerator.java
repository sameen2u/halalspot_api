package com.halal.sa.core;

import org.springframework.http.ResponseEntity;

import com.halal.sa.core.exception.ApiException;

public interface ApiResponseGenerator {
	
	public abstract <T> ResponseEntity<T> generateResponse(ApiRequest apiRequest, ApiResponse<T> apiResponse) throws ApiException;


}
