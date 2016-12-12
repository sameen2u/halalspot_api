package com.halal.sa.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.halal.sa.controller.vo.BizCategoryVO;
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
	
	/*
	 * endpoint - HalalApi/v1/business/search?address=camp,pune,india&keyword=chinese
	 * endpoint - HalalApi/v1/business/search?lat=18.345&lng=-80.3242&keyword=chinese
	 */
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<Object> searchBusinessExecute(@RequestParam MultiValueMap<String, String> requestParameters,
													 @RequestHeader HttpHeaders headers) throws ApiException, BadRequestException{
		ApiRequest apiRequest = new ApiRequest(requestParameters, headers);
		return apiController.execute(apiRequest, searchBusinessApiWorkflow);
	}
	
	/*
	 * this method used for profile page data 
	 * endpoint - HalalApi/v1/business/search/marietta/123
	 */
	@RequestMapping(value="/search/{city}/{profileId}", method=RequestMethod.GET)
	public ResponseEntity<Object> searchBusinessProfileExecute(@PathVariable("city") String city,
						@PathVariable("profileId") int profile_id) throws ApiException{
		BusinessVO businessVO = searchBusinessProcessor.searchSingleBusiness(city, profile_id);
		return new ResponseEntity<Object>(businessVO, HttpStatus.OK);
	}
	
	/*
	 * this method is used for auto populating data for keywords search with term in for the city 
	 * endpoint - HalalApi/v1/business/search/marietta?term=chi
	 */
	@RequestMapping(value="/search/{city}", method=RequestMethod.GET)
	public @ResponseBody KeywordSearchVO searchBusinessKeyword(@PathVariable("city") String city,
								@RequestParam String term) throws ApiException{
		KeywordSearchVO keywordSearchVo = searchBusinessProcessor.searchKeyword(city, term);
		return keywordSearchVo;
	}
	
	/*
	 * 
	 */
	@RequestMapping(value="/search/cat", method=RequestMethod.GET)
	public ResponseEntity<Object> searchBusinessCategories(@RequestParam(name="lat", required=true) String lat, 
			@RequestParam(name="lng", required=true) String lng) throws ApiException, BadRequestException{
		List catList = searchBusinessProcessor.searchBizCategories(lat, lng);
		return new ResponseEntity<Object>(catList, HttpStatus.OK);
	}
	
}
