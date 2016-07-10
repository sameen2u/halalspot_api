package com.halal.sa.service;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.common.error.ApiException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class ThirdPartyServiceTest {
	
	@Autowired
	ThirdPartyService thirdPartyService;

	/*
	 * testing method getLongiLatitude with valid address info
	 */
	@Test
	public void getLongiLatitude_with_valid_address() throws ApiException {
		Map coordinates = thirdPartyService.getLongiLatitude("camp+pune+india");
		Map location = (Map) coordinates.get("coordinates");
		assertTrue(location.get("lat").equals(18.5122306));
		assertTrue(location.get("lng").equals(73.88601));
		
	}
	
}
