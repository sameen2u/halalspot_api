package com.halal.sa.core.apiprocessor;


import com.halal.sa.core.AggregateData;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.ApiResponse;
import com.halal.sa.core.RequestParameters;
import com.halal.sa.core.exception.ApiException;

public interface ApiPostProcessor {
	
	public abstract <T> ApiResponse<T> postProcess(ApiRequest apiRequest,RequestParameters requestParameters, AggregateData aggregateDataObject) throws ApiException;
	
	public abstract <T> T transform(RequestParameters requestParameters, AggregateData aggregateData) throws ApiException;	
	

}
