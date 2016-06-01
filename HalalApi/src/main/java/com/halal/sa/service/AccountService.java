package com.halal.sa.service;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.halal.sa.common.ApiConstant;
import com.halal.sa.common.CommonUtil;
import com.halal.sa.common.error.ApiException;
import com.halal.sa.common.error.ErrorConstants;
import com.halal.sa.common.error.ErrorResponse;
import com.halal.sa.controller.AccountController;
import com.halal.sa.controller.vo.LogonVO;
import com.halal.sa.controller.vo.UserVO;
import com.halal.sa.controller.vo.response.UserAuthentication;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.processor.ApiErrorProcessor;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.dao.impl.AccountDaoImpl;
import com.halal.sa.data.entities.User;
import com.sun.org.apache.bcel.internal.generic.RETURN;

@Service("accountService")
public class AccountService extends BaseService{
	
	private final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	AccountDaoImpl accountDao;
	
	@Autowired
	@Qualifier("error.processor")
	ApiErrorProcessor eApiErrorProcessor;
	
	/**
	 * service register method will save the user info to DB 	
	 * @param userVO
	 * @return
	 * @throws ApiException 
	 */
	public ResponseEntity<Object> register(UserVO userVO) throws ApiException{
		LOGGER.debug("inside register method of Account Service class");
		try{
			String hashPassword = CommonUtil.hashPassword(userVO.getPassword());
			userVO.setPassword(hashPassword);
			if(validateRegistrationData(userVO)){
				return processResponseEntity(accountDao.insertUserData(userVO),HttpStatus.OK);
			}
			else{
				Object resObject = eApiErrorProcessor.processError(null, new ApiException(ErrorConstants.ERRORCODE_INVALID_DATA,ErrorConstants.ERRORDESC_EMAIL_ALREADY_EXIST));
				return processResponseEntity(resObject, HttpStatus.OK);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			//sending generic error in case of exception in Dao method
			ErrorResponse errorResponse = new ErrorResponse();
			return processResponseEntity(errorResponse,HttpStatus.OK);
		}
	}
	
	/**
	 * This method will validate the user data and avoid duplicate registration
	 */
	private boolean validateRegistrationData(UserVO userVO){
		//if email exist then return false
		if(accountDao.getUserByEmail(userVO.getEmail())!=null){
			return false;
		}
		else
			return true;
	}
	
	/**
	 * This method validate the user password after hashing it
	 * @param request
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws ApiException 
	 */
	public ResponseEntity<Object> login(LogonVO logonVO, HttpServletRequest request) throws NoSuchAlgorithmException, ApiException{
		LOGGER.debug("Inside login method in AccountService class");
		String hashedPassword = CommonUtil.hashPassword(logonVO.getPassword());
		User user = (User) accountDao.getUserByPassword(logonVO.getUsername(), hashedPassword);
		if(user !=null ){	
			String userId=user.get_id();
			String activityToken=null;
			String sessionToken=generateToken(userId);
			//generating tokens based on remember me flag 
			if(logonVO.getRememberMe()){
				activityToken =generateToken(userId);
			}
			if(sessionToken.length()>0){
				user = accountDao.updateToken(user, sessionToken, activityToken);
			}
		}
			return processResponse(user, request);
	}
	
	/**
	 * This method will process the logic of remember me check box during login
	 * #need to implement
	 */
	public void rememberUser(){
		
	}

	/**
	 * This method process the response for the login authentication
	 * @throws ApiException 
	 */
			
	protected ResponseEntity<Object> processResponse(Object model, HttpServletRequest request) throws ApiException {
		//if data is returned means auth successful
		UserAuthentication userAuthentication = null;
		User user = (User) model;
		if(user != null){
			//#need to call remember me method here.
//			rememberUser();
			userAuthentication = new UserAuthentication();
			userAuthentication.setUserId(user.get_id());
			userAuthentication.setEmail(user.getEmail());
			userAuthentication.setName(user.getFullname());
			//session & activity token are encoded before sending back in response
			userAuthentication.setSessionToken(CommonUtil.encriptString(user.getSessionToken()));
			userAuthentication.setUserActivityToken(CommonUtil.encriptString(user.getUserActivityToken()));
//			userAuthentication.setUserId(user.getUId());
			
		}
		return processResponseEntity(userAuthentication, HttpStatus.OK);
//		else {
//			Object resObject = eApiErrorProcessor.processError(null, new ApiException(ErrorConstants.ERRORCODE_AUTHENTICATION_FAILED,ErrorConstants.ERRORDESC_AUTHENTICATION_FAILED));
//			return processResponseEntity(resObject, HttpStatus.OK);
//		}
	}
	
	/**
	 * this method will validate the user activity token for the User id 
	 * @param userId
	 * @param token
	 * @return
	 * @throws ParseException 
	 */
	public boolean validateActivityToken(int userId ,String activityToken) throws ParseException{
		User user=null; //accountDao.getUserById(userId);
		LOGGER.debug("Validating Session Token - "+user.getUserActivityToken());
		if(!ObjectUtils.isEmpty(user) && !StringUtils.isEmpty(user.getUserActivityToken())){
			//decoding the input token before validating
			activityToken=CommonUtil.decryptString(activityToken);
			if(user.getUserActivityToken().equals(activityToken)){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
				String[] ss=activityToken.split("/");
		        int userIdOfToken = Integer.parseInt(ss[1]);
		        Date tokendate = formatter.parse(ss[2]);
		        if(userId == userIdOfToken && !isTokenExpired(tokendate, ApiConstant.API_TOKEN_TYPE_ACTIVITY)){
		        	return true;
		        }
			}
		}
			return false;		
	}
	
	/**
	 * this method will validate the user session token for the User id 
	 * @return
	 */
	public boolean validateSessionToken(int userId ,String sessionToken){
		User user = null;//accountDao.getUserById(userId);
		LOGGER.debug("Validating Session Token - "+user.getSessionToken());
		if(!ObjectUtils.isEmpty(user) && !StringUtils.isEmpty(user.getSessionToken())){
			sessionToken= CommonUtil.decryptString(sessionToken);
			if(user.getSessionToken().equals(sessionToken)){
				//#need to put session validate logic whenever needed
				return true;
			}
		}
			return false;		
	}
	
	public static boolean isTokenExpired(Date tokenDate, String tokenType){
		Calendar cal = Calendar.getInstance();
		if(tokenType.equals(ApiConstant.API_TOKEN_TYPE_SESSION)){
			//need to update session token logic
		}
		if(tokenType.equals(ApiConstant.API_TOKEN_TYPE_ACTIVITY)){
			cal.add(Calendar.DATE, -60);
		    Date expiredDate = cal.getTime();
		    if(tokenDate.before(expiredDate)){
	        	System.out.println("token expired");
	        	return true;
	        }
		}		
		return false;
	}
	
	/**
	 * this method will create the user token can be used for session/activity
	 * @return
	 */
	public String generateToken(String userId){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
		//appending date and time to validate the expiry of token 
		String tokenCreateDate = formatter.format(new Date());
		//logic to create the token
		String generatedToken = UUID.randomUUID().toString().toUpperCase()+"/"+userId+"/"+tokenCreateDate;
		//assigned the token value to a String and returned to void complexity
		return generatedToken;
//		accountDao.updateToken(userId, tokenType, generatedToken);
	}
	
	
	/**
	 * this method will removes the user session/activity token for the User id 
	 * @return
	 */
	public void removeToken(int userId ,String tokenType){
//		accountDao.updateToken(userId, tokenType, "");
	}
	
	/**
	 * this method will check if email is already registered in the DB 
	 * @return
	 */
	public String checkEmail(String email){
		if(StringUtils.isEmpty(accountDao.getUserByEmail(email))){
			System.out.println("email ---------------"+accountDao.getUserByEmail(email));
			return "false";
		}
		else 
			return "true";
	}

	@Override
	protected SearchRequestParameters validate(ApiRequest apiRequest)
			throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}


	
}
