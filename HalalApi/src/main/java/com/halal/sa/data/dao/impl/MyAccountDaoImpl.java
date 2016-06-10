package com.halal.sa.data.dao.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.controller.vo.UserVO;
import com.halal.sa.data.dao.AccountDao;
import com.halal.sa.data.entities.User;

@Repository("accountDaoImpl")
public class MyAccountDaoImpl implements AccountDao{
	private final Logger LOGGER = LoggerFactory.getLogger(MyAccountDaoImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;

	/*
	 * returns success if the data inserted successfully
	 * @see com.halal.sa.data.dao.AccountDao#insertUserData(com.halal.sa.controller.model.UserVO)
	 */
	public String insertUserData(UserVO userVO) throws ApiException {
		User user = new User();
		user.setFullname(userVO.getFullName());
		user.setEmail(userVO.getEmail());
		user.setPassword(userVO.getPassword());
		user.setCreatedDate(new Date());
		
		try{
			mongoTemplate.save(user);
			return "success";
		}
		catch(Exception e){
			LOGGER.error("ERR_MONGODB_UNAVAILABLE", e);
			throw new ApiException("ERR_MONGODB_UNAVAILABLE", "ERR_MONGODB_UNAVAILABLE");
		}
		
	}
	
	/**
	 * this method will return the User object upon successful email and password match
	 * @throws ApiException 
	 */

	public Object getUserByPassword(String userEmail, String password) throws ApiException{
		LOGGER.info("Inside loginDao method");
		Query query = new Query(Criteria.where("email").is(userEmail).and("password").is(password));
		
		try{
			List<User> users = mongoTemplate.find(query, User.class);
			if(!users.isEmpty()){
				return users.get(0);
			}
			return null;
		}
		catch(Exception e){
			LOGGER.error("ERR_MONGODB_UNAVAILABLE", e);
			throw new ApiException("ERR_MONGODB_UNAVAILABLE", "ERR_MONGODB_UNAVAILABLE");
		}
	}

	/**
	 * this method will return email if present in DB or null will be returned
	 * @throws ApiException 
	 */
	public User getUserByEmail(String email) throws ApiException {
		LOGGER.debug("Inside getUserByEmail method in class AccountDaoImpl");
		Query query = new Query(Criteria.where("email").is(email));
		
		try{
			List<User> users = mongoTemplate.find(query, User.class);
			if(users != null && !users.isEmpty()){
				return users.get(0);
			}
			return null;
		}
		catch(Exception e){
			LOGGER.error("ERR_MONGODB_UNAVAILABLE", e);
			throw new ApiException("ERR_MONGODB_UNAVAILABLE", "ERR_MONGODB_UNAVAILABLE");
		}
	}
	
	/**
	 * this method will return email if present in DB or null will be returned
	 * @throws ApiException 
	 */
	public User getUserById(String userId) throws ApiException {
		LOGGER.debug("Inside getUserByEmail method in class AccountDaoImpl");
		
		try{
			User user = mongoTemplate.findById(userId, User.class);
			return user;
		}
		catch(Exception e){
			LOGGER.error("ERR_MONGODB_UNAVAILABLE", e);
			throw new ApiException("ERR_MONGODB_UNAVAILABLE", "ERR_MONGODB_UNAVAILABLE");
		}
	}
	
	
	/**
	 * It inserts the session token or activity token to DB
	 * @param userId
	 * @param sessionToken
	 * @param activityToken
	 * @throws ApiException 
	 */
	@Transactional
	public User updateToken(User user, String sessionToken, String activityToken) throws ApiException{
			user.setSessionToken(sessionToken);
			user.setUserActivityToken(activityToken);
			
			try{
				mongoTemplate.save(user);
				return user;
			}
			catch(Exception e){
				LOGGER.error("ERR_MONGODB_UNAVAILABLE", e);
				throw new ApiException("ERR_MONGODB_UNAVAILABLE", "ERR_MONGODB_UNAVAILABLE");
			}
	}
}
