package com.sa.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.halal.sa.service.ThirdPartyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class GoogleApiTest {
	
	@Autowired
	ThirdPartyService thirdPartyService;
	
//	@Test
	public void test(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(
		        "https://maps.googleapis.com/maps/api/geocode/json?address=321+ghorpade+Peth,+pune,+maharashtra,+411042",
		        String.class);

		System.out.println(response);
	}
	
	@Test
	public void googleTest(){
		System.out.println(thirdPartyService.getLongiLatitude("iman Nagar, Ward No. 8, Sanjay Nagar Pashan, Pashan, Pune, Maharashtra,india"));
	}
	
}
