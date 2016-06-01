package com.halal.sa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.ApiWorkflow;

@Component
public interface ApiController {

	public <T> ResponseEntity<T> execute(ApiRequest apiRequest, ApiWorkflow apiWorkflow);
}
