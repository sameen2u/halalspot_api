package com.halal.sa.controller;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class SearchBusinessControllerTest {

	@Autowired
	SearchBusinessController searchBusinessController;
	
	/*
	 * test searchBusinessController method with valid address and keyword
	 */
	@Test
	public void searchBusinessController_withValid_search_data() throws ApiException, BadRequestException {
		MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<String, String>();
		requestParameters.add("address", "camp,pune,india");
		requestParameters.add("keyword", "chinese");
		SearchBusinessAggregateData searchBusinessAggregateData = (SearchBusinessAggregateData) searchBusinessController.searchBusinessExecute(requestParameters, null).getBody();
		assertTrue(searchBusinessAggregateData.getSearchBusinesses().size() > 0);
		assertNotNull(searchBusinessAggregateData.getSearchReport().getRecordsPerPage());
	}
	
	/*
	 * test searchBusinessController method with empty address and keyword
	 */
	@Test
	public void searchBusinessController_with_empty_address_data() throws ApiException, BadRequestException {
		MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<String, String>();
		requestParameters.add("address", "");
		requestParameters.add("keyword", "chinese");
		try{
			ErrorResponse errorResponse = (ErrorResponse) searchBusinessController.searchBusinessExecute(requestParameters, null).getBody();
		}
		catch(BadRequestException e){
			assertTrue(e.getMessage().equals(ErrorConstants.ERRORDESC_MANDATORY_PARAM_MISSING));
		}
	}
	
	/*
	 * test searchBusinessController method with empty address and keyword
	 */
	@Test
	public void searchBusinessController_with_nonIndian_address_data() throws ApiException, BadRequestException {
		MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<String, String>();
		requestParameters.add("address", "marietta, GA, USA");
		requestParameters.add("keyword", "chinese");
		SearchBusinessAggregateData searchBusinessAggregateData = (SearchBusinessAggregateData) searchBusinessController.searchBusinessExecute(requestParameters, null).getBody();
		assertNull(searchBusinessAggregateData.getSearchBusinesses());
		assertNotNull(searchBusinessAggregateData.getSearchReport().getRecordsPerPage());
	}

}
