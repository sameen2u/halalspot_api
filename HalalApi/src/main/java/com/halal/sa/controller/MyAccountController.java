package com.halal.sa.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.common.error.ApiLoggingConstants;
import com.halal.sa.common.error.DefaultErrorProcessorImpl;
import com.halal.sa.common.error.ErrorConstants;
import com.halal.sa.common.error.ErrorResponse;
import com.halal.sa.controller.vo.LogonVO;
import com.halal.sa.controller.vo.UserVO;
import com.halal.sa.core.ApiControllerImpl;
import com.halal.sa.service.MyAccountService;


@RestController
@RequestMapping("/v1/account")
public class MyAccountController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiControllerImpl.class);
	
	@Autowired
	MyAccountService accountService;
	
	@Autowired
	DefaultErrorProcessorImpl defaultErrorProcessorImpl;
	
	/**
	 * This method will sign up/register the new user account
	 * @param userBean
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public <T> ResponseEntity<T> registerUser(@RequestBody UserVO userBean) throws ApiException{
		ResponseEntity<T> response = null;
		
		try {
			response = (ResponseEntity<T>) accountService.register(userBean);
			
		} 
		catch (ApiException ae){
			LOGGER.error(ae.getErrorCode(), ae);
			ErrorResponse errorResponse = defaultErrorProcessorImpl.buildErrorResponse(ae);
			response = (ResponseEntity<T>) getErrorResponse(errorResponse);
		}
		catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.error(ApiLoggingConstants.API_RESPONSE_GENERATION_FAILED
					+ e.getMessage());
			response = (ResponseEntity<T>) getErrorResponse(null);
		}
		
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
		try{
			response = (ResponseEntity<T>) accountService.loginAuthentication(logonVO, request);
		}
		catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			LOGGER.error(ApiLoggingConstants.API_RESPONSE_GENERATION_FAILED
					+ e.getMessage());
			response = (ResponseEntity<T>) getErrorResponse(null);
		}
		return response;		
	}
	
	/*
	 * This method will check if email is available to register/sign up
	 */
	@RequestMapping(value="/checkemail/{email}", method=RequestMethod.GET)
	public <T> ResponseEntity<T> checkEmail(@PathVariable String email) throws NoSuchAlgorithmException{
		ResponseEntity<T> response = null;
		try {
			response = (ResponseEntity<T>) accountService.checkEmail(email);
		} catch (ApiException ae) {
			LOGGER.error(ae.getErrorCode(), ae);
			ErrorResponse errorResponse = defaultErrorProcessorImpl.buildErrorResponse(ae);
			response = (ResponseEntity<T>) getErrorResponse(errorResponse);
		}
		return response;
	}
	
	/*
	 * This method will validate the activity and session token required to check the validity of user session
	 */
	@RequestMapping(value="/validateToken", method=RequestMethod.GET)
	public ResponseEntity<Object> validateUserToken(HttpServletRequest request, 
									@RequestParam(value="userid", required=true) int userId,
									@RequestParam(value="activitytoken", required=false) String activityToken,
									@RequestParam(value="sessiontoken", required=false) String sessionToken) throws ParseException{
		if((StringUtils.isEmpty(sessionToken) && StringUtils.isEmpty(activityToken))){
			ErrorResponse errorResponse = new ErrorResponse(404,"Please pass sessiontoken");
			return accountService.processResponseEntity(errorResponse, HttpStatus.FORBIDDEN);
		}
			Map<String,String> mapObj = new HashMap<String,String>();
			if(accountService.validateSessionToken(userId, sessionToken)){
				mapObj.put("sessionToken", "success");
			}
			else
				mapObj.put("sessionToken", "failure");
			if(accountService.validateActivityToken(userId, activityToken)){
				mapObj.put("activitytoken", "success");
			}	
			else
				mapObj.put("activitytoken", "failure");
			return accountService.processResponseEntity(mapObj, HttpStatus.OK);
	}
	
	//need to implement 
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ResponseEntity<Object> logout(@RequestParam String id) {
		System.out.println("------------------------Logout done for id = "+id+"------------------");
		return null;
	}
	
private ResponseEntity<ErrorResponse> getErrorResponse(ErrorResponse errorResponseObj){
		
		if(errorResponseObj == null){
			errorResponseObj = new ErrorResponse(
					ErrorConstants.ERRORCODE_INTERNAL_ERROR,
					ErrorConstants.ERRORDESC_INTERNAL_ERROR);
		}
			
		 
		 ResponseEntity<ErrorResponse> errorResponse= new ResponseEntity<>(errorResponseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		return errorResponse;
		
	}
	
}
