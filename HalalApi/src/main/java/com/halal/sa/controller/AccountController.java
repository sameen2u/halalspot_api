package com.halal.sa.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.common.error.ErrorResponse;
import com.halal.sa.controller.vo.LogonVO;
import com.halal.sa.controller.vo.UserVO;
import com.halal.sa.service.AccountService;


@RestController
@RequestMapping("/v1/account")
public class AccountController{

	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ResponseEntity<Object> registerUser(@RequestBody UserVO userBean){
		return accountService.register(userBean);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Object> loginAuthentication(@RequestBody LogonVO logonVO, HttpServletRequest request) throws NoSuchAlgorithmException{
		return accountService.login(logonVO, request);
	}
	
	@RequestMapping(value="/checkemail/{email}", method=RequestMethod.GET)
	public String checkEmail(@PathVariable String email) throws NoSuchAlgorithmException{
		return accountService.checkEmail(email);
	}
	
	@RequestMapping(value="/validateToken", method=RequestMethod.GET)
	public ResponseEntity<Object> validateUserToken(HttpServletRequest request, 
									@RequestParam(value="userid", required=true) int userId,
									@RequestParam(value="activitytoken", required=false) String activityToken,
									@RequestParam(value="sessiontoken", required=false) String sessionToken) throws ParseException{
		if((StringUtils.isEmpty(sessionToken) && StringUtils.isEmpty(activityToken))){
			ErrorResponse errorResponse = new ErrorResponse("API_ERR01","Please pass sessiontoken");
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
	
}
