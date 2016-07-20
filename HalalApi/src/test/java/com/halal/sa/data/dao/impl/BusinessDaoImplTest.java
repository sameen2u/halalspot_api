package com.halal.sa.data.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.core.exception.ApiException;
import com.halal.sa.data.dao.SearchBusinessDao;
import com.halal.sa.data.entities.Business;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class BusinessDaoImplTest {
	
	@Autowired
	BusinessDaoImpl businessDaoImpl;
	
	@Autowired
	SearchBusinessDao searchBusinessDao;
	
	List idList;
	
	@Before
	public void setUp(){
		idList = new ArrayList<>();
		idList.add("5772ce806d855e56c89e67d1");
		idList.add("5772ceaf6d855e56c89e67d2");
		idList.add("5772d0236d85f38f51902c82");
		
	}
	
	/*
	 * testing searchBusinessByKeyword with valid parameters
	 */
	@Test
	public void searchBusinessByKeyword_with_valid_data() throws ApiException {
		assertNotNull(businessDaoImpl.searchBusinessByKeyword("chaat", idList));
		
	}
	
	/*
	 * testing searchBusinessByKeyword with null values in parameters
	 */
	@Test(expected=ApiException.class)
	public void searchBusinessByKeyword_with_null_data() throws ApiException {
		assertNotNull(businessDaoImpl.searchBusinessByKeyword("", null));
		
	}
	
	/*
	 * testing searchBusinessByKeyword with valid parameters
	 */
	@Test
	public void searchBusinessByLocation_with_valid_data() throws ApiException {
		assertNotNull(businessDaoImpl.searchBusinessByLocation(73.886010, 18.512231, 5));
		
	}
	
	/*
	 * testing searchBusinessByKeyword with zero values in parameters
	 */
	@Test
	public void searchBusinessByLocation_with_null_data() throws ApiException {
		double longitude = 0;
		double latitude = 0;
		assertNotNull(businessDaoImpl.searchBusinessByLocation(longitude, latitude, 5));
		assertTrue(true);
		
	}
	
	
	/*
	 * testing searchBusinessProfile with zero values in parameters
	 */
	@Test
	public void searchBusinessProfile_with_valid_data(){
		Business business = searchBusinessDao.findByBusinessCodeAndProfileId("pune", 5);
		System.out.println(business.getEmail());
	}
}
