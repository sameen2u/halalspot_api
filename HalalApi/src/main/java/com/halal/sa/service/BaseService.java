package com.halal.sa.service;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.dao.impl.MyAccountDaoImpl;

public abstract class BaseService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MyAccountDaoImpl.class);
	
	/*
	 * this method will massage the DB data and construct the response according to contract
	 */
	protected abstract Object processResponse(Object dbObject, HttpServletRequest request) throws ApiException;
	
	/*
	 * This method club the response body object and status code with response entity
	 */
	public ResponseEntity<Object> processResponseEntity(Object responseObj, HttpStatus httpStatus) {
		return new ResponseEntity<Object>(responseObj,httpStatus);
	}
	
	protected abstract SearchRequestParameters validate(ApiRequest apiRequest) throws ApiException;

}
