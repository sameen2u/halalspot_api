package com.halal.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.halal.sa.service.SearchBusinessService;

@RestController
@RequestMapping("/v1/business")
public class SearchBusinessController {
	
	@Autowired
	SearchBusinessService searchBusinessService;
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<Object> registerRestaurant(@RequestParam(value="address",required=true) String address, 
													 @RequestParam(value="keyword",required=false) String keyword, 
													 @RequestParam int distance,
													 @RequestParam int skipRecords){
		return (ResponseEntity<Object>) searchBusinessService.searchbusinesses(keyword, address, distance, skipRecords);
	}
}
