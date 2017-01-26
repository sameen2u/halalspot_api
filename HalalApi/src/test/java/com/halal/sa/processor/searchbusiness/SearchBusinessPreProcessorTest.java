package com.halal.sa.processor.searchbusiness;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;
import com.halal.sa.core.request.SearchRequestParameters;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class SearchBusinessPreProcessorTest {
	
	@Autowired
	SearchBusinessPreProcessor searchBusinessPreProcessor;

	/*
	 * test validate method 
	 */
	@Test
	public void validate__with_valid_data() throws ApiException, BadRequestException {
		ApiRequest request = new ApiRequest();
		MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<String, String>();
		requestParameters.add("address", "camp pune india");
		requestParameters.add("keyword", "chinese");
		requestParameters.add("page", "1");
//		requestParameters.add("distance", "5");
		request.setRequestParameters(requestParameters);
		SearchRequestParameters searchRequestParameters = (SearchRequestParameters) searchBusinessPreProcessor.validate(request);
		assertNotNull(searchRequestParameters.getAddress());
		assertNotNull(searchRequestParameters.getKeyword());
	}
	
	/*
	 * test validate method 
	 */
	@Test(expected=ApiException.class)
	public void validate__with_no_address() throws ApiException, BadRequestException {
		ApiRequest request = new ApiRequest();
		MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<String, String>();
		requestParameters.add("keyword", "chinese");
		requestParameters.add("page", "1");
		request.setRequestParameters(requestParameters);
		SearchRequestParameters searchRequestParameters = (SearchRequestParameters) searchBusinessPreProcessor.validate(request);
	}
	
}
