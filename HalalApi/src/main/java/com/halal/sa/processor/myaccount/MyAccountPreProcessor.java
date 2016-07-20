package com.halal.sa.processor.myaccount;

import org.springframework.stereotype.Component;

import com.halal.sa.core.AbstractPreProcessor;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.RequestParameters;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.request.SearchRequestParameters;

@Component
public class MyAccountPreProcessor extends AbstractPreProcessor{

	/**
	 * Builds and returns the RequestParameters implementation for Search halal biz API.
	 */
	@Override
	public RequestParameters validate(ApiRequest apiRequest)
			throws ApiException {
		if(apiRequest.getRequestParameters().getFirst("address") == null){
			throw new ApiException("ERROR_BAD_REQUEST","Mandatory parameter \"address\" is missing in the request");
		}
		SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
		searchRequestParameters.setAddress(apiRequest.getRequestParameters().getFirst("address"));
		searchRequestParameters.setKeyword(apiRequest.getRequestParameters().getFirst("keyword"));
		searchRequestParameters.setRadius(apiRequest.getRequestParameters().getFirst("distance"));
		searchRequestParameters.setPage(apiRequest.getRequestParameters().getFirst("page"));
		return searchRequestParameters;
	}

	@Override
	public void setDefaultValues(RequestParameters requestParameters) {
		// TODO Auto-generated method stub
		
	}

}
