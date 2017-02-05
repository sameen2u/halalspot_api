package com.halal.sa.data.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.core.exception.ApiException;
import com.halal.sa.data.dao.SearchBusinessDao;
import com.halal.sa.data.entities.Business;
import com.mongodb.DBObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dispatcher-servlet.xml")
public class BusinessDaoImplTest {
	
	@Autowired
	BusinessDaoImpl businessDaoImpl;
	
	@Autowired
	SearchBusinessDao searchBusinessDao;
	
	List idList;
	
	@Before
	public void setUp(){
		idList = new ArrayList<>();
		idList.add(new ObjectId("589726dc431f588008e1f6de"));
//		idList.add("5772ceaf6d855e56c89e67d2");
//		idList.add("5772d0236d85f38f51902c82");
		
	}
	
	/*
	 * testing searchBusinessByKeyword with valid parameters
	 */
	@Test
	public void searchBusinessByKeyword_with_valid_data() throws ApiException {
		assertEquals(1, businessDaoImpl.searchBusinessByKeyword("Api", idList).size());
		
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
		List<DBObject> dbObjectList = businessDaoImpl.searchBusinessByLocation(00.0, 90.0, 5, "mi","");
		DBObject dbObject = dbObjectList.get(0);
		assertEquals("Do Not Delete", dbObject.get("name"));		
	}
	
	/*
	 * testing searchBusinessByKeyword with zero values in parameters
	 */
	@Test
	public void searchBusinessByLocation_with_null_data() throws ApiException {
		double longitude = 0;
		double latitude = 0;
		assertNotNull(businessDaoImpl.searchBusinessByLocation(longitude, latitude, 5,"mi",""));
		assertTrue(true);
		
	}
	
	
	/*
	 * testing searchBusinessProfile with zero values in parameters
	 */
	@Test
	public void searchBusinessProfile_with_valid_data(){
		Business business = searchBusinessDao.findByBusinessCodeAndProfileId("North Pole", 53);
		assertEquals("Do Not Delete", business.getName());
	}
	
	/*
	 * testing searchKeywordRestaurantName with valid values in parameters
	 */
	@Test
	public void searchKeywordRestaurantName_with_valid_data(){
		List<Business> businessList = searchBusinessDao.searchKeywordBusinessName("North Pole", "do");
		Business business = businessList.get(0);
		assertEquals("Do Not Delete", business.getName());
	}
	
	/*
	 * testing searchKeywordRestaurantName with valid values in parameters
	 */
	@Test
	public void test_searchBusinessCategories_with_valid_data(){
		List<Business> businessList = businessDaoImpl.searchBusinessCategories( 00.0, 90.0, 5, "mi","");
		System.out.println(businessList.size());
		
	}
}
