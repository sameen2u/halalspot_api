package com.halal.sa.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.halal.sa.data.dao.impl.AccountDaoImpl;
import com.halal.sa.data.entities.User;

public abstract class BaseService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AccountDaoImpl.class);
	
	/*
	 * this method will massage the DB data and construct the response according to contract
	 */
	protected abstract Object processResponse(Object dbObject, HttpServletRequest request);
	
	/*
	 * This method club the response body object and status code with response entity
	 */
	public ResponseEntity<Object> processResponseEntity(Object responseObj, HttpStatus statucCode){
		return new ResponseEntity<Object>(responseObj,statucCode);
	}
	
	protected abstract boolean validate(Object model);

}
