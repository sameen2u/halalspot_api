package com.halal.sa.data.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.controller.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class MyAccountDaoImplTest {
	
	@Autowired
	MyAccountDaoImpl myAccountDaoImpl;
	
	@Before
	public void setUp(){
		
	}
	
	/*
	 * testing method getUserByPassword with valid user login 
	 */
//	@Test
	public void insertUser_with_valid_data() throws ApiException {
		UserVO userVO = new UserVO();
		userVO.setEmail("test1");
		userVO.setFullName("Test1");
		userVO.setPassword("test1");
		myAccountDaoImpl.insertUserData(userVO);
		assertTrue(true);
	}
	
	/*
	 * testing method getUserByPassword with valid user login 
	 */
	@Test
	public void getUserByPassword_with_valid_data() throws ApiException {
		assertNotNull(myAccountDaoImpl.getUserByPassword("test", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"));
	}
	
	/*
	 * testing method getUserByPassword with null 
	 */
	@Test
	public void getUserByPassword_with_null_data() throws ApiException {
		
		myAccountDaoImpl.getUserByPassword(null, null);
		assertTrue(true);
	}
	
	/*
	 * testing method getUserByEmail with valid user login 
	 */
	@Test
	public void getUserByEmail_with_valid_data() throws ApiException {
		assertNotNull(myAccountDaoImpl.getUserByEmail("test"));
	}
	
	/*
	 * testing method getUserByEmail with null 
	 */
	@Test
	public void getUserByEmail_with_null_data() throws ApiException {
		myAccountDaoImpl.getUserByEmail(null);
		assertTrue(true);
	}
	
	/*
	 * testing method getUserById with valid 
	 */
	@Test
	public void getUserById_with_valid_data() throws ApiException {
		assertNotNull(myAccountDaoImpl.getUserById("5772c9fa6d855e56c89e67d0"));
	}
	
	/*
	 * testing method getUserById with null 
	 */
	@Test
	public void getUserById_with_null_data() throws ApiException {
		myAccountDaoImpl.getUserById(null);
		assertTrue(true);
		
	}

}
