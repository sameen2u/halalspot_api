package com.halal.sa.core.apiprocessor;

import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.RequestParameters;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;

public interface ApiPreProcessor {

	public abstract RequestParameters validate(ApiRequest apiRequest) throws ApiException, BadRequestException;
	
	public abstract void setDefaultValues(RequestParameters requestParameters);
	
	public abstract RequestParameters preProcess(ApiRequest apiRequest) throws ApiException, BadRequestException;
}
