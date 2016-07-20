package com.halal.sa.controller;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.halal.sa.controller.vo.LogonVO;
import com.halal.sa.controller.vo.response.UserAuthentication;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.ErrorCode;
import com.halal.sa.core.exception.ErrorConstants;
import com.halal.sa.core.exception.ErrorResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class MyAccountControllerTest {

	@Autowired
	MyAccountController myAccountController;
	
	MockHttpServletRequest mockedRequest = new MockHttpServletRequest();
	
	/*
	 * test loginAuth methd with valid data
	 */
	@Test
	public void loginAuthentication_with_Valid_Login_Details_RememberMe_true() throws NoSuchAlgorithmException, ApiException {
		LogonVO logonVO = new LogonVO();
		logonVO.setUsername("test");
		logonVO.setPassword("test");
		logonVO.setRememberMe(Boolean.TRUE);
		UserAuthentication userAuthentication = (UserAuthentication) myAccountController.loginAuthentication(logonVO, mockedRequest).getBody();
		assertNotNull(userAuthentication.getUserId());
		assertNotNull(userAuthentication.getUserActivityToken());
	}
	
	/*
	 * test loginAuth methd with valid data
	 */
	@Test
	public void loginAuthentication_with_Valid_Login_Details_RememberMe_false() throws NoSuchAlgorithmException, ApiException {
		LogonVO logonVO = new LogonVO();
		logonVO.setUsername("test");
		logonVO.setPassword("test");
		logonVO.setRememberMe(Boolean.FALSE);
		UserAuthentication userAuthentication = (UserAuthentication) myAccountController.loginAuthentication(logonVO, mockedRequest).getBody();
		assertNotNull(userAuthentication.getUserId());
		assertNull(userAuthentication.getUserActivityToken());
	}
	
	/*
	 * test loginAuth methd with empty login data
	 */
	@Test()
	public void loginAuthentication_with_empty_LogonVO() throws NoSuchAlgorithmException, ApiException {
		LogonVO logonVO = new LogonVO();
		logonVO.setUsername("");
		logonVO.setPassword("");
		logonVO.setRememberMe(Boolean.FALSE);
		try{
			ErrorResponse errorResponse = (ErrorResponse) myAccountController.loginAuthentication(logonVO, mockedRequest).getBody();
		}
		catch(ApiException e){
			assertTrue(e.getMessage().equals(ErrorConstants.ERRORDESC_LOGIN_EMAIL_OR_PASSWORD_MISSING));
		}
	}
	
	/*
	 * test loginAuth method with new login email and password
	 */
	@Test
	public void loginAuthentication_with_new_Login_Details() throws NoSuchAlgorithmException, ApiException {
		LogonVO logonVO = new LogonVO();
		logonVO.setUsername("xxx");
		logonVO.setPassword("xxx");
		logonVO.setRememberMe(Boolean.FALSE);
		UserAuthentication userAuthentication = (UserAuthentication) myAccountController.loginAuthentication(logonVO, mockedRequest).getBody();
		assertTrue(userAuthentication.getError().equals("ERRORCODE_EMAIL_NOT_FOUND"));
		assertTrue(userAuthentication.getErrorDescription().equals("Email not registered. Please signup."));
	}

	/*
	 * test loginAuth method with new login email and password
	 */
	@Test
	public void loginAuthentication_with_InValid_Login_Details() throws NoSuchAlgorithmException, ApiException {
		LogonVO logonVO = new LogonVO();
		logonVO.setUsername("test");
		logonVO.setPassword("xxx");
		logonVO.setRememberMe(Boolean.FALSE);
		UserAuthentication userAuthentication = (UserAuthentication) myAccountController.loginAuthentication(logonVO, mockedRequest).getBody();
		assertTrue(userAuthentication.getError().equals("AUTHENTICATION_FAILED"));
		assertTrue(userAuthentication.getErrorDescription().equals("Authentication Failed: Email and Password dont Match"));
	}


}
