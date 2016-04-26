package com.halal.sa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.halal.sa.data.entities.Business;
import com.halal.sa.service.BusinessService;

@RestController
@RequestMapping("/v1/ad")
public class BusinessController{
	
	@Autowired
	BusinessService businessService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<Object> registerRestaurant(@RequestBody Business business, HttpServletRequest request){
		request.setAttribute("method", "register");
		return businessService.registerBusiness(business,request);
	}
	
}
