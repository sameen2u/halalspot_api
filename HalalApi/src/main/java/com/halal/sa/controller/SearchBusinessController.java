package com.halal.sa.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.halal.sa.DELETE.SearchBusinessService;
import com.halal.sa.common.error.ApiException;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.ApiWorkflow;

@RestController
@RequestMapping("/v1/business")
public class SearchBusinessController {
	
//	@Autowired
//	SearchBusinessService searchBusinessService;
	
	@Autowired
	private ApiController apiController;
	
	@Resource(name="searchBusinessApiWorkflow")
	private ApiWorkflow searchBusinessApiWorkflow;
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<Object> registerRestaurant(@RequestParam MultiValueMap<String, String> requestParameters,
													 @RequestHeader HttpHeaders headers) throws ApiException{
//													 @RequestParam(value="address",required=true) String address, 
		ApiRequest apiRequest = new ApiRequest(requestParameters, headers);
		//return (ResponseEntity<Object>) searchBusinessService.searchbusinesses(apiRequest);
		return apiController.execute(apiRequest, searchBusinessApiWorkflow);
	}
}
