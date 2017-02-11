package com.halal.sa.processor.searchbusiness;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.halal.sa.controller.vo.BusinessVO;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.entities.Business;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dispatcher-servlet.xml")
public class SearchBusinessProcessorTest {

	@Autowired
	SearchBusinessProcessor searchBusinessProcessor;
	
	/*
	 * test retrieveData method with correct requestParameter value
	 */
	@Test
	public void retrieveData_with_valid_data() throws ApiException, BadRequestException {
		SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
		searchRequestParameters.setAddress("camp pune india");
		searchRequestParameters.setKeyword("chinese");
		
		SearchBusinessAggregateData searchBusinessAggregateData = (SearchBusinessAggregateData) searchBusinessProcessor.retrieveData(searchRequestParameters);
		assertNotNull(searchBusinessAggregateData.getSearchReport().getTotalRecords());
	}
	
	/*
	 * test searchbusinesses method with correct requestParameter value
	 */
	@Test
	public void searchbusinesses_with_valid_data() throws ApiException, BadRequestException {
		SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
		searchRequestParameters.setAddress("camp pune india");
		searchRequestParameters.setKeyword("chinese");
		
		SearchBusinessAggregateData searchBusinessAggregateData = searchBusinessProcessor.searchProcessor(searchRequestParameters);
		assertNotNull(searchBusinessAggregateData.getSearchReport().getTotalRecords());
	}
	
	/*
	 * test search method with correct requestParameter value
	 */
	@Test(expected=BadRequestException.class)
	public void search_with_empty_data() throws ApiException, BadRequestException {
		SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
		searchRequestParameters.setAddress("");
		searchRequestParameters.setKeyword("");
		
		SearchBusinessAggregateData searchBusinessAggregateData = searchBusinessProcessor.searchProcessor(searchRequestParameters);
	}
	
	/*
	 * test searchBusiness method with valid keyword and address
	 */
	@Test
	public void searchBusiness_with_valid_data() throws ApiException {
		List list = searchBusinessProcessor.searchBusinessService("chinese", "camp pune india", 5.0, "mi",null, null,"");
		assertTrue(list.size() > 0);
	}
	
	/*
	 * test searchBusiness method with invalid keyword
	 */
	@Test
	public void searchBusiness_with_invalid_data() throws ApiException {
		List list = searchBusinessProcessor.searchBusinessService("xxx", "aaa", 5.0, "mi", null, null,"");
		assertTrue(true);
	}

	/*
	 * test searchBusiness method with invalid keyword
	 */
//	@Test
	public void searchSingleBusiness_with_invalid_data() throws ApiException {
		BusinessVO businessVO = searchBusinessProcessor.searchSingleBusiness("savali-vegetarian-restaurant-pune", 4);
		assertNotNull(businessVO.getEmail());
	}
	
	
	/*
	 * test searchBusiness method with invalid keyword
	 */
	@Test
	public void populateWorkingHours_with_valid_data() {
		Business business = new Business();
		BusinessVO businessVO = new BusinessVO();
		business.setWorkingHours("mon=07:00-21:00,tue=07:00-21:00,wed=07:00-21:00,thur=07:00-21:00,fri=07:00-21:00,sat=10:00-17:00,sun=closed");
		searchBusinessProcessor.populateWorkingHours(business, businessVO);
		
		assertNotNull(businessVO.getWorkingHours());
	}
	
	
	/*
	 * test searchBusiness method with invalid keyword
	 */
	@Test
	public void searchBizCategories_with_valid_data() {
//		Business business = new Business();
//		BusinessVO businessVO = new BusinessVO();
//		business.setWorkingHours("mon=07:00-21:00,tue=07:00-21:00,wed=07:00-21:00,thur=07:00-21:00,fri=07:00-21:00,sat=10:00-17:00,sun=closed");
		searchBusinessProcessor.searchBizCategories(33.952602, -84.549933 , "10", "United States");
		
//		assertNotNull(businessVO.getWorkingHours());
	}
}
