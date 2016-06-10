package com.halal.sa.service;

import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.halal.sa.common.ApiConstant;
import com.halal.sa.common.error.ApiException;
import com.halal.sa.controller.vo.LogonVO;
import com.halal.sa.service.MyAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class MyAccountServiceTest {
	
	@Autowired
	MyAccountService accountService;
	
	/*
	 * loginAuthentication test with valid login details
	 */
	@Test
	public void loginAuthentication_withValid_data() throws NoSuchAlgorithmException, ApiException{
		LogonVO logonVO = new LogonVO();
		logonVO.setUsername("test");
		logonVO.setPassword("test");
		HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockedRequest.getAttribute("authentication")).thenReturn("true");
		assertTrue(accountService.loginAuthentication(logonVO, mockedRequest).getStatusCode().equals(HttpStatus.OK));
	}
	
	/*
	 * loginAuthentication test with empty username and password
	 */
	@Test
	public void loginAuthentication_with_null_data() throws NoSuchAlgorithmException, ApiException{
		LogonVO logonVO = new LogonVO();
		logonVO.setUsername("");
		logonVO.setPassword("");
		HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
		
		assertTrue(accountService.loginAuthentication(logonVO, mockedRequest).getStatusCode().equals(HttpStatus.NOT_FOUND));
	}
	
	/*
	 * loginAuthentication test with correct login details
	 */
	@Test
	public void loginAuthentication_with_invalid_password() throws NoSuchAlgorithmException, ApiException{
		LogonVO logonVO = new LogonVO();
		logonVO.setUsername("test");
		logonVO.setPassword("t");
		HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
		
		assertTrue(accountService.loginAuthentication(logonVO, mockedRequest).getStatusCode().equals(HttpStatus.UNAUTHORIZED));
	}
	
	/*
	 * validateToken test with valid activity token to test
	 */
	@Test
	public void validateToken_withValid_activity_token() throws ApiException, ParseException{
		Map response = (Map) accountService.validateTokens("57068fd92ece683e24e9f6b0",
				"M0E3NzFFQUMtODA3MS00N0EwLTlEOUQtQ0M1QkRFMzI4OTI4LzU3MDY4ZmQ5MmVjZTY4M2UyNGU5ZjZiMC8yMDE2LTA2LTA3LDE2OjI0OjA5", "").getBody();
		assertTrue(response.get("activitytoken").equals("success"));
	}
	
	/*
	 * validateToken test with valid session token to test
	 */
	@Test
	public void validateToken_withValid_session_token() throws ApiException, ParseException{
		Map response = (Map) accountService.validateTokens("57068fd92ece683e24e9f6b0",
				"", "Nzg2QTE1MTctNjRDRi00RjRELTkzNTAtRjhDNzRGMjkxNjMzLzU3MDY4ZmQ5MmVjZTY4M2UyNGU5ZjZiMC8yMDE2LTA2LTA3LDE2OjI0OjA5").getBody();
		assertTrue(response.get("sessionToken").equals("success"));
	}
	
	@Test(expected=ApiException.class)
	public void validateToken_with_null_tokens() throws ApiException, ParseException{
		accountService.validateTokens("57068fd92ece683e24e9f6b0","", "");
	}
	
	/*
	 * isTokenExpired test with valid data
	 */
	@Test()
	public void isTokenExpired_with_valid_token_date_should_return_true() throws ApiException, ParseException{
		Date tokenDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(tokenDate);
		cal.add(Calendar.DATE, -59);
		tokenDate = cal.getTime();
		assertTrue(accountService.isTokenExpired(tokenDate, ApiConstant.API_TOKEN_TYPE_ACTIVITY) == true);
	}
	
	/*
	 * isTokenExpired test with invalid data
	 */
	@Test()
	public void isTokenExpired_with_expired_token_date_should_return_false() throws ApiException, ParseException{
		Date tokenDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(tokenDate);
		cal.add(Calendar.DATE, -61);
		tokenDate = cal.getTime();
		assertTrue(accountService.isTokenExpired(tokenDate, ApiConstant.API_TOKEN_TYPE_ACTIVITY) == false);
	}
	
	/*
	 * checkEmail test with valid email
	 */
	@Test()
	public void checkEmail_with_valid_email() throws ApiException, ParseException{
		Map response = (Map) accountService.checkEmail("sameen@gmail.com").getBody();
		assertTrue(response.get("available").equals("false"));
	}
	
	/*
	 * checkEmail test with invalid email
	 */
	@Test()
	public void checkEmail_with_invalid_email() throws ApiException, ParseException{
		Map response = (Map) accountService.checkEmail("xxx").getBody();
		assertTrue(response.get("available").equals("true"));
	}
	
	/*
	 * checkEmail test with invalid email
	 */
	@Test(expected=ApiException.class)
	public void checkEmail_with_null_email() throws ApiException, ParseException{
		Map response = (Map) accountService.checkEmail("").getBody();
	}
	
}
