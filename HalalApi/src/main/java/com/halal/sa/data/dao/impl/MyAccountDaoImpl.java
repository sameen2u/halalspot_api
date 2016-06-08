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
	public String insertUserData(UserVO userVO) {
		User user = new User();
		user.setFullname(userVO.getFullName());
		user.setEmail(userVO.getEmail());
		user.setPassword(userVO.getPassword());
		user.setCreatedDate(new Date());
		mongoTemplate.save(user);
		return "success";
	}
	
	/**
	 * this method will return the User object upon successful email and password match
	 */

	public Object getUserByPassword(String userEmail, String password){
		LOGGER.info("Inside loginDao method");
		LOGGER.debug("Inside loginDao method");
		Query query = new Query(Criteria.where("email").is(userEmail).and("password").is(password));
		List<User> users = mongoTemplate.find(query, User.class);
		if(!users.isEmpty()){
			return users.get(0);
		}
		return null;
	}

	/**
	 * this method will return email if present in DB or null will be returned
	 */
	public User getUserByEmail(String email) {
		LOGGER.debug("Inside getUserByEmail method in class AccountDaoImpl");
		Query query = new Query(Criteria.where("email").is(email));
		List<User> users = mongoTemplate.find(query, User.class);
		if(users != null && !users.isEmpty()){
			return users.get(0);
		}
		return null;
	}
	
	
	/**
	 * It inserts the session token or activity token to DB
	 * @param userId
	 * @param sessionToken
	 * @param activityToken
	 */
	@Transactional
	public User updateToken(User user, String sessionToken, String activityToken){
			user.setSessionToken(sessionToken);
			user.setUserActivityToken(activityToken);
			mongoTemplate.save(user);
			return user;
	}
}
