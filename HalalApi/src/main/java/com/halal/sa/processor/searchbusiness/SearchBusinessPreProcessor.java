package com.halal.sa.processor.searchbusiness;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.core.AbstractPreProcessor;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.RequestParameters;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.dao.impl.MyAccountDaoImpl;

@Component
public class SearchBusinessPreProcessor extends AbstractPreProcessor{
	
	private final Logger LOGGER = LoggerFactory.getLogger(SearchBusinessPreProcessor.class);

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
		searchRequestParameters.setLattitude(apiRequest.getRequestParameters().getFirst("lattitude"));
		searchRequestParameters.setLongitude(apiRequest.getRequestParameters().getFirst("longitude"));
		LOGGER.info("Request Parameter for searching the business - "+searchRequestParameters.toString());
		return searchRequestParameters;
	}

	@Override
	public void setDefaultValues(RequestParameters requestParameters) {
		// TODO Auto-generated method stub
		
	}

}
