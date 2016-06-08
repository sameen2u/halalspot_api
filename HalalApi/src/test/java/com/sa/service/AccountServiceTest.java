package com.sa.service;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.controller.vo.LogonVO;
import com.halal.sa.service.MyAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class AccountServiceTest {
	
	@Autowired
	MyAccountService accountService;
	
	@Test
	public void loginTest() throws NoSuchAlgorithmException{
		LogonVO logonVO = new LogonVO();
		logonVO.setUsername("nadeem@gmail.com");
		logonVO.setPassword("shabina");
//		String pass = (String) accountService.login(logonVO);
//		System.out.println("password -"+pass);
	}
	
//	@Test
	public void validateToken(){
		System.out.println("Response : "+accountService.validateSessionToken(1,"testsession"));
	}
	
//	@Test
	public void deleteToken(){
		accountService.removeToken(1, "session");
	}
	
//	@Test
	public void createToken(){
		accountService.generateToken("3");
	}

}
