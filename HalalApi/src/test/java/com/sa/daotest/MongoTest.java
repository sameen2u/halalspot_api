package com.sa.daotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.controller.vo.UserVO;
import com.halal.sa.data.dao.impl.MongoRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class MongoTest {
	
	@Autowired
	MongoRepo mongoRepo;
	
//	@Test
	public void addDatatest(){
		UserVO userVO = new UserVO();
		userVO.setFullName("sameen");
		userVO.setEmail("nadeem@gmail.com");
		userVO.setPassword("lubna");
		mongoRepo.addData(userVO);
	}
	
//	@Test
	public void getDatatest(){
		System.out.println("Email - "+mongoRepo.getData("57057ed22ece85412ab5a6c7").getEmail());
	}
	
	@Test
	public void getDataEmailtest(){
		System.out.println("Email - "+mongoRepo.getDataByEmail("sameen").getEmail());
	}
	
	@Test
	public void addNumbersTest(){
		mongoRepo.addNumbers(5, 5);
	}

}
