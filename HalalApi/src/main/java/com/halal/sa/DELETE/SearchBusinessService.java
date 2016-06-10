package com.halal.sa.DELETE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;

import com.halal.sa.common.ApplicationConstant;
import com.halal.sa.common.CommonUtil;
import com.halal.sa.common.error.ApiException;
import com.halal.sa.controller.vo.response.SearchBusiness;
import com.halal.sa.controller.vo.response.SearchReport;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.dao.BusinessDao;
import com.halal.sa.data.dao.impl.BusinessDaoImpl;
import com.halal.sa.data.entities.Address;
import com.halal.sa.processor.searchbusiness.SearchBusinessAggregateData;
import com.halal.sa.service.BaseService;
import com.halal.sa.service.ThirdPartyService;
import com.mongodb.DBObject;

@Service
public class SearchBusinessService extends BaseService{
	
	String KEYWORD=null;
	int PAGE=0;
	
	@Autowired
	BusinessDaoImpl businessDaoImpl;
	
	@Autowired
	ThirdPartyService thirdPartyService;
	
	@Override
	protected Object processResponse(Object model, HttpServletRequest request) {
		List<DBObject> dbObjectList = (List<DBObject>) model;
		SearchBusinessAggregateData searchBusinessResult = null;
		
		
		SearchBusiness searchBusiness;
		List<SearchBusiness> searchBusinesses;
		
		if(dbObjectList !=null && dbObjectList.size()>0){
			searchBusinesses = new ArrayList<SearchBusiness>();
			int recordLimit = ApplicationConstant.BUSINESS_RECORDS_PER_PAGE;
			for(DBObject dbObject: dbObjectList){
				int record = 1;
//				if(dbObjectList.size() > recordLimit){
//					searchReport.setNextPage(true);
//				}
				searchBusiness = new SearchBusiness();
				Address address = new Address();
				Map<String,Object> resultAddress= (Map<String, Object>) dbObject.get("address");
				if( resultAddress!= null){
					if(resultAddress.get("streetAddress") != null){
						address.setStreetAddress((String)resultAddress.get("streetAddress"));
					}
					if(resultAddress.get("city") != null){
						address.setCity((String)resultAddress.get("city"));
					}
					if(resultAddress.get("pincode") != null){
						address.setPincode((int) resultAddress.get("pincode"));
					}
					if(resultAddress.get("landmark") != null){
						address.setLandmark((String)resultAddress.get("landmark"));
					}
					if(resultAddress.get("locality") != null){
						address.setLocality((String)resultAddress.get("locality"));
					}
					
					searchBusiness.setAddress(address);
				}
				
				if(dbObject.get("name") != null){
					searchBusiness.setName((String)dbObject.get("name"));
				}
				if(dbObject.get("phone") != null){
					searchBusiness.setPhone((int)dbObject.get("phone"));
				}
				if(dbObject.get("cuisine") != null){
					searchBusiness.setCuisine((List)dbObject.get("cuisine"));
				}
				if(dbObject.get("authenticity") != null){
					searchBusiness.setAuthenticity((String)dbObject.get("authenticity"));
				}
				if(dbObject.get("status") != null){
					searchBusiness.setStatus((String)dbObject.get("status"));
				}
				double distance = (double) dbObject.get("distance");
				//rounding logic
				distance= Math.round(distance * 100.0) / 100.0;
				if(dbObject.get("distance") != null){
					searchBusiness.setDistance(Double.toString(distance));
				}
//				searchBusiness.setCuisine(dbObject.get("features").toString());
//				searchBusiness.setWorkingHours(dbObject.get("working hours").toString());
				searchBusinesses.add(searchBusiness);
				//logic for pagination( if number of index == recordLimit means record limit perpage is over
				if(searchBusinesses.size() == recordLimit){
					break;
				}
			}
			searchBusinessResult = new SearchBusinessAggregateData();
			SearchReport searchReport = new SearchReport();
			searchBusinessResult.setSearchBusinesses(searchBusinesses);
			searchReport.setKeyword(KEYWORD);
			searchReport.setCurrentPage(PAGE);
			//logic for pagination( if number of records > recordLimit means one more page can be retrieved and next page pagination should be shown on UI
			searchReport.setRecordsPerPage(ApplicationConstant.BUSINESS_RECORDS_PER_PAGE);
			
			searchBusinessResult.setSearchReport(searchReport);
		}
		
		return searchBusinessResult;
	}

	@Override
	//done
	protected SearchRequestParameters validate(ApiRequest apiRequest) throws ApiException {
		if(apiRequest.getRequestParameters().getFirst("address") == null){
			throw new ApiException("ERROR_BAD_REQUEST","Mandatory parameter \"address\" is missing in the request");
		}
		SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
		searchRequestParameters.setAddress(apiRequest.getRequestParameters().getFirst("address"));
		searchRequestParameters.setKeyword(apiRequest.getRequestParameters().getFirst("keyword"));
		searchRequestParameters.setKeyword(apiRequest.getRequestParameters().getFirst("distance"));
		searchRequestParameters.setKeyword(apiRequest.getRequestParameters().getFirst("page"));
		return searchRequestParameters;
	}
	
	/**
	 * This the main Search method returning the response based on Api request input
	 * @param apiRequest
	 * @return
	 * @throws ApiException
	 */
	public Object searchbusinesses(ApiRequest apiRequest) throws ApiException{
		SearchRequestParameters searchRequestParameters = this.validate(apiRequest);
		String keyword = apiRequest.getRequestParameters().getFirst("keyword");
		String address = apiRequest.getRequestParameters().getFirst("keyword");
		String distance = searchRequestParameters.getRadius();
		if( distance!=null && Integer.parseInt(distance) < 1){
			distance = ApplicationConstant.BUSINESS_DEFAULT__DISTANCE_RADIUS;
		}
		apiRequest.getRequestParameters().getFirst("keyword");
		String page = apiRequest.getRequestParameters().getFirst("keyword");
//		KEYWORD = requestParameters.get("keyword");
//		PAGE = page;
		List businesses = this.searchBusiness(keyword, address, Double.parseDouble(distance));
		Object object = processResponse(businesses, null);
		return processResponseEntity(object, HttpStatus.OK);
	}
	
	/**
	 * This is the main method returns the list of business based on the address and keyword if passed 
	 * It calls 2 dao methods
	 * 1. get list of all business near the lang and lat of the address
	 * 2. get list of business contains the keyword in above list of biz 
	 * and then filter the records which doesnt have the keywords 
	 * @throws ApiException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List searchBusiness(String keyword, String address, double distance) throws ApiException {
		List businessIds = null;
		List<DBObject> businessByDistance = null;
		List<DBObject> businessByKeyword = null;
		List<DBObject> resultBusiness=null;
		double longitude;
		double latitude;
		Map<String,Object> map = thirdPartyService.getLongiLatitude(address);
		if(map !=null && map.size() >0){
			Map coordinate = (Map)map.get("coordinates");
			longitude = (double) coordinate.get("lng");
			latitude = (double) coordinate.get("lat");
			businessByDistance= businessDaoImpl.searchBusinessByLocation(longitude, latitude, distance);
		}
		if(businessByDistance !=null && businessByDistance.size()>0 && StringUtils.isNotEmpty(keyword)){
			businessIds = new ArrayList<>();
			for(DBObject dbObject: businessByDistance){
				businessIds.add(dbObject.get("_id"));
			}
				businessByKeyword = businessDaoImpl.searchBusinessByKeyword(keyword, businessIds);
		}
		if(businessByKeyword!=null && !businessByKeyword.isEmpty()){
			resultBusiness = new ArrayList<DBObject>();
			for(DBObject distObject: businessByDistance){
				for(DBObject keyObject: businessByKeyword){
					if(distObject.get("_id").equals(keyObject.get("_id"))){
						resultBusiness.add(distObject);
						break;
					}
				}
			}
		}
		
		return resultBusiness;		
	}
	
}
