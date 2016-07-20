package com.halal.sa.core.apiprocessor;

import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.RequestParameters;
import com.halal.sa.core.exception.ApiException;

public interface ApiPreProcessor {

	public abstract RequestParameters validate(ApiRequest apiRequest) throws ApiException;
	
	public abstract void setDefaultValues(RequestParameters requestParameters);
	
	public abstract RequestParameters preProcess(ApiRequest apiRequest) throws ApiException;
}
