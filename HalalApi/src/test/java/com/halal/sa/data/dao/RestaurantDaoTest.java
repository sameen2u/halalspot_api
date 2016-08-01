package com.halal.sa.data.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class RestaurantDaoTest {
	
	@Autowired
	RestaurantDao restaurantDao;

	@Test
	public void test_findCuisineKeywords() {
		List  list = restaurantDao.findCuisineKeywords("ka");
		System.out.println(list.size());
	}

}
