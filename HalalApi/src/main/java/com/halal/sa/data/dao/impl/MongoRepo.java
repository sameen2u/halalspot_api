package com.halal.sa.data.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.util.DBObjectUtils;
import org.springframework.stereotype.Repository;

import com.halal.sa.controller.vo.UserVO;
import com.halal.sa.data.entities.User;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;

@Repository
public class MongoRepo {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public void addData(UserVO userVO){
		User user = new User();
		user.setFullname(userVO.getFullName());
		user.setEmail(userVO.getEmail());
		user.setPassword(userVO.getPassword());
		user.setCreatedDate(new Date());
		mongoTemplate.save(user);
	}
	
	public User getData(String id){
		
//		Query query = new Query(Criteria.where("username").is("mkyong"));
		User user2= mongoTemplate.findById(id, User.class);//(query, User2.class);
		return user2;
	}
	
	public User getDataByEmail(String email){
		
		Query query = new Query(Criteria.where("fullname").is("sameen"));
		List<User> list= mongoTemplate.find(query, User.class);
		return list.get(0);
	}
	
	public void addNumbers(int x, int y){
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.append("$eval", "addNumbers("+x+","+y+")");
		
		CommandResult result = mongoTemplate.executeCommand(dbObject);
		Object o =  result.get("retval");
		System.out.println(o.toString());
	}
	

}
