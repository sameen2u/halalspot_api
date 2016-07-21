package com.halal.sa.service;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.data.entities.Address;
import com.halal.sa.data.entities.Business;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class BusinessServiceTest {
	
	@Autowired
	BusinessService businessService;

	/*
	 * test validate method with null Business obj to see all the validation errors
	 */
//	@Test
	public void validate_with_all_null_in_Business_obj() {
		Business business = new Business();
		Address address = new Address();
		business.setAddress(address);
		Map map = (Map) businessService.validate(business);
		assertTrue(map.size() == 4);
		
	}
	
	/*
	 * test validate method with mandatory values in Business obj
	 */
	@Test
	public void validate_with_all_mandatory_values_in_Business_obj() {
		Business business = new Business();
		business.setName("Test");
		business.setEmail("test");
		Address address = new Address();
		address.setStreetAddress("test");
		business.setAddress(address);
		Map map = (Map) businessService.validate(business);
		assertTrue(map.size() == 0);
		
	}
	
	/*
	 * test constructGoogleApiAddressInUrl method with valid data
	 */
	@Test
	public void constructGoogleApiAddressInUrl_with_all_mandatory_values_in_Business_obj() {
		Business business = new Business();
		Address address = new Address();
		address.setStreetAddress("Camp pune India");
		business.setAddress(address);
		String fomattedAddr = businessService.constructGoogleApiAddressInUrl(business);
		assertTrue(fomattedAddr.equals("Camp+pune+India"));
		
	}

}
