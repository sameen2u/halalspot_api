package com.halal.sa.core.apiprocessor;


import com.halal.sa.core.AggregateData;
import com.halal.sa.core.RequestParameters;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;

public interface ApiProcessor {

	public abstract AggregateData retrieveData(RequestParameters requestParameters) throws ApiException, BadRequestException;
	
	public abstract AggregateData process(RequestParameters requestParameters) throws ApiException, BadRequestException;

}
