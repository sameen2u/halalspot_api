package com.halal.sa.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.halal.sa.controller.vo.BusinessVO;
import com.halal.sa.controller.vo.KeywordSearchVO;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.ApiWorkflow;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;
import com.halal.sa.processor.searchbusiness.SearchBusinessProcessor;

@RestController
@RequestMapping("/v1/business")
public class SearchBusinessController {
	
	@Autowired
	private ApiController apiController;
	
	@Resource(name="searchBusinessApiWorkflow")
	private ApiWorkflow searchBusinessApiWorkflow;
	
	@Autowired
	SearchBusinessProcessor searchBusinessProcessor;
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<Object> searchBusinessExecute(@RequestParam MultiValueMap<String, String> requestParameters,
													 @RequestHeader HttpHeaders headers) throws ApiException, BadRequestException{
		ApiRequest apiRequest = new ApiRequest(requestParameters, headers);
		//return (ResponseEntity<Object>) searchBusinessService.searchbusinesses(apiRequest);
		return apiController.execute(apiRequest, searchBusinessApiWorkflow);
	}
	
	@RequestMapping(value="/search/{city}/{profileId}", method=RequestMethod.GET)
	public ResponseEntity<Object> searchBusinessProfileExecute(@PathVariable("city") String city,
						@PathVariable("profileId") int profile_id) throws ApiException{
		BusinessVO businessVO = searchBusinessProcessor.searchSingleBusiness(city, profile_id);
		return new ResponseEntity<Object>(businessVO, HttpStatus.OK);
//		return apiController.execute(null, searchBusinessApiWorkflow);
	}
	
	@RequestMapping(value="/search/{city}/key/{keywordInitials}", method=RequestMethod.GET)
	public ResponseEntity<Object> searchRestaurantKeyword(@PathVariable("city") String city,
						@PathVariable("keywordInitials") String keywordInitials) throws ApiException{
		KeywordSearchVO keywordSearchVo = searchBusinessProcessor.searchKeyword(city, keywordInitials);
		return new ResponseEntity<Object>(keywordSearchVo, HttpStatus.OK);
	}
}
