package com.sa.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.service.SearchBusinessService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class SearchServiceTest {
	
	@Autowired
	SearchBusinessService searchBusinessService;

	@Test
	public void test() {
//		searchBusinessService.searchbusinesses("kabab", "camp+pune+india", 5,4);
	}

}
