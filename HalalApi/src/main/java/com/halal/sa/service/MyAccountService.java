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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

import com.halal.sa.common.ApiConstant;
import com.halal.sa.common.CommonUtil;
import com.halal.sa.common.error.ApiException;
import com.halal.sa.common.error.DomainErrorConstants;
import com.halal.sa.common.error.ErrorCode;
import com.halal.sa.common.error.ErrorConstants;
import com.halal.sa.common.error.ErrorResponse;
import com.halal.sa.controller.MyAccountController;
import com.halal.sa.controller.vo.LogonVO;
import com.halal.sa.controller.vo.UserVO;
import com.halal.sa.controller.vo.response.UserAuthentication;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.apiprocessor.ApiErrorProcessor;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.dao.impl.MyAccountDaoImpl;
import com.halal.sa.data.entities.User;
import com.sun.org.apache.bcel.internal.generic.RETURN;

@Service("accountService")
public class MyAccountService extends BaseService{
	
	private final Logger LOGGER = LoggerFactory.getLogger(MyAccountService.class);
	
	@Autowired
	MyAccountDaoImpl accountDao;
	
	@Autowired
	@Qualifier("error.processor")
	ApiErrorProcessor eApiErrorProcessor;
	
	/**
	 * service register method will save the user info to DB 	
	 * @param userVO
	 * @return
	 * @throws ApiException 
	 * @throws NoSuchAlgorithmException 
	 */
	public ResponseEntity<Object> register(UserVO userVO) throws ApiException, NoSuchAlgorithmException{
		LOGGER.debug("inside register method of Account Service class");
		String hashPassword = CommonUtil.hashPassword(userVO.getPassword());
		userVO.setPassword(hashPassword);
		if(!validateRegistrationData(userVO)){
			return processResponseEntity(accountDao.insertUserData(userVO),HttpStatus.OK);
		}
		else{
			throw new ApiException(ErrorCode.ERRORCODE_EMAIL_NOT_FOUND.toString(), ErrorConstants.ERRORDESC_EMAIL_ALREADY_EXIST);
		}
	}
	
	/**
	 * This method will validate the user data and avoid duplicate registration
	 * @throws ApiException 
	 */
	private boolean validateRegistrationData(UserVO userVO) throws ApiException{
		//if email exist then return false
		if(accountDao.getUserByEmail(userVO.getEmail())!=null){
			return true;
		}
		else
			return false;
	}
	
	/**
	 * This method validate the user password after hashing it
	 * @param request
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws ApiException 
	 */
	public ResponseEntity<Object> loginAuthentication(LogonVO logonVO, HttpServletRequest request) throws NoSuchAlgorithmException, ApiException{
		LOGGER.debug("Inside login method in AccountService class");
		if(logonVO == null || logonVO.getUsername().isEmpty() || logonVO.getPassword().isEmpty()){
			throw new ApiException(ErrorCode.ERRORCODE_LOGIN_EMAIL_OR_PASSWORD_MISSING.toString(), ErrorConstants.ERRORDESC_LOGIN_EMAIL_OR_PASSWORD_MISSING);
		}
		String hashedPassword = CommonUtil.hashPassword(logonVO.getPassword());
		User user = (User) accountDao.getUserByEmail(logonVO.getUsername());
		if(user != null && StringUtils.equals(hashedPassword, user.getPassword())){
			request.setAttribute("authentication", "true");
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
		UserAuthentication userAuthentication = new UserAuthentication();
		User user = (User) model;
		boolean authentication = false;
		HttpStatus httpStatus;
		if(request != null && request.getAttribute("authentication") !=null){
			 authentication = Boolean.parseBoolean(request.getAttribute("authentication").toString());
		}
		
		if(user == null){
			userAuthentication.setError(ErrorCode.ERRORCODE_EMAIL_NOT_FOUND.toString());
			userAuthentication.setErrorDescription(ErrorConstants.ERRORDESC_EMAIL_NOT_FOUND);
			httpStatus = HttpStatus.NOT_FOUND;
		}
		else if(authentication){
			//#need to call remember me method here.
//			rememberUser();
			userAuthentication.setUserId(user.get_id());
			userAuthentication.setEmail(user.getEmail());
			userAuthentication.setName(user.getFullname());
			//session & activity token are encoded before sending back in response
			userAuthentication.setSessionToken(CommonUtil.encriptString(user.getSessionToken()));
			userAuthentication.setUserActivityToken(CommonUtil.encriptString(user.getUserActivityToken()));
//			userAuthentication.setUserId(user.getUId());
			httpStatus = HttpStatus.OK;
			
		}
		else {
			userAuthentication.setError(ErrorConstants.ERRORCODE_AUTHENTICATION_FAILED);
			userAuthentication.setErrorDescription(ErrorConstants.ERRORDESC_AUTHENTICATION_FAILED);
			httpStatus = HttpStatus.UNAUTHORIZED;
		}
		return processResponseEntity(userAuthentication, httpStatus);
	}
	
	/**
	 * This is common method to validate sessions and activity token
	 * @return
	 * @throws ParseException 
	 * @throws ApiException 
	 */
	public ResponseEntity<Object> validateTokens(String userId, String activityToken, String sessionToken) throws ParseException, ApiException{
		
		if((StringUtils.isEmpty(sessionToken) && StringUtils.isEmpty(activityToken))){
			throw new ApiException(DomainErrorConstants.ERRCODE_BAD_REQUEST, DomainErrorConstants.ERRDESC_TOKEN_MISSING);
		}
			Map<String,String> mapObj = new HashMap<String,String>();
			if(!StringUtils.isEmpty(sessionToken) && this.validateSessionToken(userId, sessionToken)){
				mapObj.put("sessionToken", "success");
			}else{
				mapObj.put("sessionToken", "failure");
			}
				
			if(!StringUtils.isEmpty(activityToken) && this.validateActivityToken(userId, activityToken)){
				mapObj.put("activitytoken", "success");
			}else{
				mapObj.put("activitytoken", "failure");
			}
		
		return this.processResponseEntity(mapObj, HttpStatus.OK);
		
	}
	
	/**
	 * this method will validate the user activity token for the User id 
	 * @param userId
	 * @param token
	 * @return
	 * @throws ParseException 
	 * @throws ApiException 
	 */
	public boolean validateActivityToken(String userId ,String activityToken) throws ParseException, ApiException{
		User user= accountDao.getUserById(userId);
		LOGGER.debug("Validating Activity Token - "+activityToken+" for user id - "+userId);
		if(user != null && !StringUtils.isEmpty(user.getUserActivityToken())){
			//decoding the input token before validating
			activityToken=CommonUtil.decryptString(activityToken);
			if(user.getUserActivityToken().equals(activityToken)){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
				String[] ss=activityToken.split("/");
		        String userIdOfToken = ss[1].toString();
		        Date tokendate = formatter.parse(ss[2]);
		        if(StringUtils.equals(userId, userIdOfToken) && isTokenExpired(tokendate, ApiConstant.API_TOKEN_TYPE_ACTIVITY)){
		        	return true;
		        }
			}
		}
			return false;		
	}
	
	/**
	 * this method will validate the user session token for the User id 
	 * @return
	 * @throws ApiException 
	 */
	public boolean validateSessionToken(String userId ,String sessionToken) throws ApiException{
		User user = accountDao.getUserById(userId);
		if(!ObjectUtils.isEmpty(user) && !StringUtils.isEmpty(user.getSessionToken())){
			LOGGER.debug("Validating Session Token - "+user.getSessionToken());
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
		    Date expiryDate = cal.getTime();
		    if(tokenDate.before(expiryDate)){
	        	return false;
	        }
		}		
		return true;
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
	 * @throws ApiException 
	 */
	public ResponseEntity<Object> checkEmail(String email) throws ApiException{
		if(email !=null && !email.isEmpty()){
			Map<String, String> response = new HashMap<String, String>();
			response.put("email", email);
			User user = accountDao.getUserByEmail(email);
			if(user == null){
				response.put("available", "true");
			}
			else {
				response.put("available", "false");
			}
			
			return processResponseEntity(response, HttpStatus.OK);	
		}
		else{
			throw new ApiException(DomainErrorConstants.ERRCODE_INCOMPLETE_DATA, ErrorConstants.ERRORDESC_INCOMPLETE_DATA);
		}
	}

	@Override
	protected SearchRequestParameters validate(ApiRequest apiRequest)
			throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
