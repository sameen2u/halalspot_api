package com.halal.sa.core.processor;


import com.halal.sa.common.error.ApiException;
import com.halal.sa.core.AggregateData;
import com.halal.sa.core.RequestParameters;

public interface ApiProcessor {

	public abstract AggregateData retrieveData(RequestParameters requestParameters) throws ApiException;
	
	public abstract AggregateData process(RequestParameters requestParameters) throws ApiException;

}
