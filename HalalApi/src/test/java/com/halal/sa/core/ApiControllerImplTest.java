package com.halal.sa.core;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;
import com.halal.sa.core.exception.ErrorConstants;
import com.halal.sa.core.exception.ErrorResponse;
import com.halal.sa.processor.searchbusiness.SearchBusinessAggregateData;
import com.halal.sa.processor.searchbusiness.SearchBusinessApiWorkflow;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class ApiControllerImplTest {
	
	@Autowired
	ApiControllerImpl apiControllerImpl;
	
	@Autowired
	SearchBusinessApiWorkflow apiWorkflow;

	/*
	 * test execute method for search business workflow with valid api request, In case of failure please check the data in mongo
	 */
	@Test
	public void execute_search_business_workflow_with_valid_data() throws ApiException, BadRequestException {
		ApiRequest request = new ApiRequest();
		MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<String, String>();
		requestParameters.add("address", "camp pune india");
		requestParameters.add("keyword", "chinese");
		requestParameters.add("page", "1");
		request.setRequestParameters(requestParameters);
		SearchBusinessAggregateData aggregateData = (SearchBusinessAggregateData) apiControllerImpl.execute(request, apiWorkflow).getBody();
		assertNotNull(aggregateData.getSearchBusinesses().size() > 0);
	}
	
	/*
	 * test execute method for search business workflow with null api request obj, In case of failure please check the data in mongo
	 */
	@Test
	public void execute_search_business_workflow_with_null_apirequest() throws ApiException, BadRequestException {
		try{
			ErrorResponse errorResponse = (ErrorResponse) apiControllerImpl.execute(null, apiWorkflow).getBody();
		}
		catch(ApiException e){
			assertTrue(e.getMessage().equals(ErrorConstants.ERR_ENCOUNTERED_DURING_PROCESSING));
		}
		
//		assertTrue(errorResponse.getId() == 500);
	}
	
	/*
	 * test execute method for search business workflow with null workflow obj, In case of failure please check the data in mongo
	 */
	@Test
	public void execute_search_business_workflow_with_null_apiworkflow() throws ApiException, BadRequestException {
		ApiRequest request = new ApiRequest();
		MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<String, String>();
		requestParameters.add("address", "camp pune india");
		requestParameters.add("keyword", "chinese");
		requestParameters.add("page", "1");
		request.setRequestParameters(requestParameters);
		try{
			ErrorResponse errorResponse = (ErrorResponse) apiControllerImpl.execute(request, null).getBody();
		}
		catch(ApiException e){
			assertTrue(e.getMessage().equals(ErrorConstants.ERR_ENCOUNTERED_DURING_PROCESSING));
		}
	}

}
