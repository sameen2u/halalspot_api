package com.halal.sa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.common.error.ApiLoggingConstants;
import com.halal.sa.common.error.DefaultErrorProcessorImpl;
import com.halal.sa.common.error.ErrorResponse;
import com.halal.sa.core.ApiControllerImpl;
import com.halal.sa.data.entities.Business;
import com.halal.sa.service.BusinessService;

@RestController
@RequestMapping("/v1/biz")
public class BusinessController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);
	
	@Autowired
	DefaultErrorProcessorImpl defaultErrorProcessorImpl;
	
	@Autowired
	BusinessService businessService;
	
	/*
	 * Method Add the business in the backend
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public <T> ResponseEntity<T> registerRestaurant(@RequestBody Business business, HttpServletRequest request){
		request.setAttribute("method", "register");
		ResponseEntity<T> response = null;
		try {
			response = (ResponseEntity<T>) businessService.registerBusiness(business,request);
		} catch (ApiException ae) {
			LOGGER.error(ae.getErrorCode(), ae);
			ErrorResponse errorResponse = defaultErrorProcessorImpl.buildErrorResponse(ae);
			response = (ResponseEntity<T>) defaultErrorProcessorImpl.getErrorResponse(errorResponse);
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.error(ApiLoggingConstants.API_RESPONSE_GENERATION_FAILED
					+ e.getMessage());
			response = (ResponseEntity<T>) defaultErrorProcessorImpl.getErrorResponse(null);
		}
		return response;
	}
	
}
