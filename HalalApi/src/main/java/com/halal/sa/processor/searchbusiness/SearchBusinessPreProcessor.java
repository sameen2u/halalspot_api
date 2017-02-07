package com.halal.sa.processor.searchbusiness;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.halal.sa.core.AbstractPreProcessor;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.RequestParameters;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.dao.impl.MyAccountDaoImpl;

@Component
public class SearchBusinessPreProcessor extends AbstractPreProcessor{
	
	private final Logger LOGGER = LoggerFactory.getLogger(SearchBusinessPreProcessor.class);

	/**
	 * Builds and returns the RequestParameters implementation for Search halal biz API.
	 * @throws BadRequestException 
	 */
	@Override
	public RequestParameters validate(ApiRequest apiRequest)
			throws ApiException, BadRequestException {
		SearchRequestParameters searchRequestParameters = null;
		if(apiRequest.getRequestParameters().getFirst("address") != null || (apiRequest.getRequestParameters().getFirst("lat") != null && apiRequest.getRequestParameters().getFirst("lng") != null)){
			searchRequestParameters = new SearchRequestParameters();
			searchRequestParameters.setAddress(apiRequest.getRequestParameters().getFirst("address"));
			searchRequestParameters.setKeyword(apiRequest.getRequestParameters().getFirst("keyword"));
			
			validateRadius(apiRequest.getRequestParameters().getFirst("radius"));
			searchRequestParameters.setRadius(apiRequest.getRequestParameters().getFirst("radius"));
			
			searchRequestParameters.setPage(apiRequest.getRequestParameters().getFirst("page"));
			if(apiRequest.getRequestParameters().getFirst("lat")!=null && apiRequest.getRequestParameters().getFirst("lng")!=null){
				searchRequestParameters.setLattitude(Double.parseDouble(apiRequest.getRequestParameters().getFirst("lat")));
				searchRequestParameters.setLongitude(Double.parseDouble(apiRequest.getRequestParameters().getFirst("lng")));
			}
			
			
			validateCategory(apiRequest.getRequestParameters().getFirst("cat"));
			searchRequestParameters.setCategory(apiRequest.getRequestParameters().getFirst("cat"));
			
			searchRequestParameters.setCountry(apiRequest.getRequestParameters().getFirst("country"));
			LOGGER.info("Request Parameter for searching the business - "+searchRequestParameters.toString());
		}
		
		else{
			throw new ApiException("ERROR_BAD_REQUEST","Mandatory parameter \"address\" or \"lat, lng\" is missing in the request");
		}
		return searchRequestParameters;
	}
	
	private void validateRadius(String distance) throws BadRequestException{
		if(StringUtils.isNotBlank(distance) && !StringUtils.isNumeric(distance)){
			throw new BadRequestException("ERROR_BAD_REQUEST","distance param should be numeric");
		}
	}
	
	private void validateCategory(String category) throws BadRequestException{
		if(StringUtils.isNotBlank(category) && !StringUtils.isAlpha(category)){
			throw new BadRequestException("ERROR_BAD_REQUEST","category param should be alpha");
		}
	}

	@Override
	public void setDefaultValues(RequestParameters requestParameters) {
		// TODO Auto-generated method stub
		
	}

}
