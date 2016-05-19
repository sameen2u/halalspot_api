package com.sa.daotest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.controller.vo.BusinessVO;
import com.halal.sa.data.dao.impl.BusinessDaoImpl;
import com.halal.sa.data.entities.Address;
import com.halal.sa.data.entities.Business;
import com.mongodb.DBObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class BusinessDaoTest {
	
	@Autowired
	BusinessDaoImpl businessDaoImpl;
	
//	@Test
	public void addBizTest(){
//		Address address = new Address("kondhwa", "", "Pune", 401, "police chowki");
		List list = new ArrayList<String>();
		list.add("chinese");
		list.add("indian");
		Map workingHours = new HashMap<>();
		workingHours.put("mon", "10-9");
		workingHours.put("tue", "10-9");
		Business business = new Business();
//		business.setAddress(address);
		business.setName("George");
		business.setCuisine(list);
		business.setEmail("george@gmail.com");
		business.setWorkingHours(workingHours);
		business.setStatus("Opened");
		business.setFacebookPage("fbtest");
		businessDaoImpl.addBusinessInfo(business);
	}
	
	@Test
	public void searchBiz(){
//		List<DBObject> dbObjects = businessDaoImpl.searchBusiness("chaat", "Camp+Pune+Maharashtra+India", 2, 5);
//		System.out.println("hotels -"+dbObjects.size());
	}
	
//	@Test
	public void searchByKeyword(){
//		List list = new ArrayList<>();
//		
//		list.add(new ObjectId("571453a82ece1392240f7b91"));
//		List<Business> businesses = businessDaoImpl.searchBusinessByKeyword("chaat",list);
//		businessDaoImpl.searchBusinessByKeyword("chaat", 0);
		List list = businessDaoImpl.searchBusinessByLocation(73.88601 , 18.5122306, 5);
		System.out.println("records -"+list.size());
	}

}
