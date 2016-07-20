package com.halal.sa.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.halal.sa.controller.vo.LogonVO;
import com.halal.sa.controller.vo.UserVO;
import com.halal.sa.core.ApiControllerImpl;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.service.MyAccountService;


@RestController
@RequestMapping("/v1/account")
public class MyAccountController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiControllerImpl.class);
	
	@Autowired
	MyAccountService accountService;
	
//	@Autowired
//	DefaultErrorProcessorImpl defaultErrorProcessorImpl;
	
	/**
	 * This method will sign up/register the new user account
	 * @param userBean
	 * @return
	 * @throws ApiException
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public <T> ResponseEntity<T> registerUser(@RequestBody UserVO userBean) throws ApiException, NoSuchAlgorithmException{
		ResponseEntity<T> response = null;
		
		response = (ResponseEntity<T>) accountService.register(userBean);
		return response;
	}
	
	/**
	 * Method authenticates the user logging into the account
	 * @param logonVO
	 * @param headers
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws ApiException
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public <T> ResponseEntity<T> loginAuthentication(@RequestBody LogonVO logonVO,  HttpServletRequest request) throws NoSuchAlgorithmException, ApiException{
		
		ResponseEntity<T> response = null;
		response = (ResponseEntity<T>) accountService.loginAuthentication(logonVO, request);
		return response;		
	}
	
	/*
	 * This method will check if email is available to register/sign up
	 */
	@RequestMapping(value="/checkemail/{email}", method=RequestMethod.GET)
	public <T> ResponseEntity<T> checkEmail(@PathVariable String email) throws NoSuchAlgorithmException, ApiException{
		ResponseEntity<T> response = null;
		response = (ResponseEntity<T>) accountService.checkEmail(email);
		return response;
	}
	
	/*
	 * This method will validate the activity and session token required to check the validity of user session
	 */
	@RequestMapping(value="/validateToken", method=RequestMethod.GET)
	public <T> ResponseEntity<T> validateUserToken(HttpServletRequest request, 
									@RequestParam(value="userid", required=true) String userId,
									@RequestParam(value="activitytoken", required=false) String activityToken,
									@RequestParam(value="sessiontoken", required=false) String sessionToken) throws ParseException, ApiException{
		ResponseEntity<T> response = null;
		response = (ResponseEntity<T>) accountService.validateTokens(userId, activityToken, sessionToken);
		return response;
		
	}
	
	//need to implement 
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ResponseEntity<Object> logout(@RequestParam String id) {
		System.out.println("------------------------Logout done for id = "+id+"------------------");
		return null;
	}
	
}
