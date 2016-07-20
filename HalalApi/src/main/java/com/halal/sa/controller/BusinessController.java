package com.halal.sa.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halal.sa.core.exception.ApiException;
import com.halal.sa.data.entities.Business;
import com.halal.sa.service.BusinessService;

@RestController
@RequestMapping("/v1/biz")
public class BusinessController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);
	
//	@Autowired
//	DefaultErrorProcessorImpl defaultErrorProcessorImpl;
	
	@Autowired
	BusinessService businessService;
	
	/*
	 * Method Add the business in the backend
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public <T> ResponseEntity<T> registerBizExecute(@RequestBody Business business, HttpServletRequest request) throws ApiException{
		request.setAttribute("method", "register");
		ResponseEntity<T> response = null;
		response = (ResponseEntity<T>) businessService.registerBusiness(business,request);
		return response;
	}
	
}
