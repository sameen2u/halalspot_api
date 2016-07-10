package com.halal.sa.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.common.error.ErrorConstants;
import com.halal.sa.common.error.ErrorResponse;
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
	public void searchBusinessController_withValid_search_data() throws ApiException {
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
	public void searchBusinessController_with_empty_address_data() throws ApiException {
		MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<String, String>();
		requestParameters.add("address", "");
		requestParameters.add("keyword", "chinese");
		ErrorResponse errorResponse = (ErrorResponse) searchBusinessController.searchBusinessExecute(requestParameters, null).getBody();
		assertTrue(errorResponse.getId().equals(500));
		assertTrue(errorResponse.getDescription().equals("ERR_BAD_REQUEST"));
	}
	
	/*
	 * test searchBusinessController method with empty address and keyword
	 */
	@Test
	public void searchBusinessController_with_nonIndian_address_data() throws ApiException {
		MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<String, String>();
		requestParameters.add("address", "marietta, GA, USA");
		requestParameters.add("keyword", "chinese");
		SearchBusinessAggregateData searchBusinessAggregateData = (SearchBusinessAggregateData) searchBusinessController.searchBusinessExecute(requestParameters, null).getBody();
		assertNull(searchBusinessAggregateData.getSearchBusinesses());
		assertNotNull(searchBusinessAggregateData.getSearchReport().getRecordsPerPage());
	}

}
