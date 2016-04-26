package com.halal.sa.core.processor;

import java.util.Map;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.core.ApiRequest;


public interface ApiErrorProcessor {

	public Object processError(final ApiRequest apiRequest, final ApiException Exception);
	
}
