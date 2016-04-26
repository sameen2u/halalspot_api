package com.sa.daotest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.controller.vo.BusinessVO;
import com.halal.sa.data.dao.impl.BusinessDaoImpl;
import com.halal.sa.data.entities.Address;
import com.halal.sa.data.entities.Business;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class BusinessDaoTest {
	
	@Autowired
	BusinessDaoImpl businessDaoImpl;
	
//	@Test
	public void addBizTest(){
		Address address = new Address("kondhwa", "", "Pune", 401, "police chowki");
		List list = new ArrayList<String>();
		list.add("chinese");
		list.add("indian");
		Map workingHours = new HashMap<>();
		workingHours.put("mon", "10-9");
		workingHours.put("tue", "10-9");
		Business business = new Business();
		business.setAddress(address);
		business.setName("George");
		business.setCuisine(list);
		business.setEmail("george@gmail.com");
		business.setWorkingHours(workingHours);
		business.setStatus("Opened");
		business.setFacebookPage("fbtest");
		businessDaoImpl.addBusinessInfo(business);
	}
	
//	@Test
	public void searchBiz(){
		System.out.println(businessDaoImpl.searchBusiness(null, 0).getName());;
	}
	
	@Test
	public void searchByKeyword(){
		List<Business> businesses = businessDaoImpl.searchBusinessByKeyword("camp");
		List<String> ids = new ArrayList<String>();
		for(Business business: businesses){
			ids.add(business.getId());
		}
//		businessDaoImpl.searchBusinessByKeyword("chaat", 0);
		businessDaoImpl.searchBusinessByLocation(73.856744 , 18.520430, 2.7, ids);
	}

}
