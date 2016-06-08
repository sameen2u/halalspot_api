package com.halal.sa.processor.searchbusiness;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.halal.sa.common.ApplicationConstant;
import com.halal.sa.common.CommonUtil;
import com.halal.sa.common.error.ApiException;
import com.halal.sa.controller.vo.response.SearchBusiness;
import com.halal.sa.controller.vo.response.SearchReport;
import com.halal.sa.core.AbstractProcessor;
import com.halal.sa.core.AggregateData;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.RequestParameters;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.dao.impl.BusinessDaoImpl;
import com.halal.sa.data.entities.Address;
import com.halal.sa.service.ThirdPartyService;
import com.mongodb.DBObject;

@Component
public class SearchBusinessProcessor extends AbstractProcessor{
	
	String KEYWORD=null;
	int PAGE=0;
	
	@Autowired
	BusinessDaoImpl businessDaoImpl;
	
	@Autowired
	ThirdPartyService thirdPartyService;
	

	@Override
	public AggregateData retrieveData(RequestParameters requestParameters)
			throws ApiException {
		SearchRequestParameters searchRequestParameters = (SearchRequestParameters) requestParameters;
		SearchBusinessAggregateData searchBusinessAggregateData = searchbusinesses(searchRequestParameters);
		return searchBusinessAggregateData;
	}
	
	/**
	 * This the main Search method returning the response based on Api request input
	 * @param apiRequest
	 * @return
	 * @throws ApiException
	 */
	public SearchBusinessAggregateData searchbusinesses(SearchRequestParameters searchRequestParameters) throws ApiException{
		String keyword = searchRequestParameters.getKeyword();
		String address = searchRequestParameters.getAddress();
		String distance = searchRequestParameters.getRadius();
		
		if( distance == null || Integer.parseInt(distance) < Integer.parseInt(ApplicationConstant.BUSINESS_DEFAULT__DISTANCE_RADIUS)){
			distance = ApplicationConstant.BUSINESS_DEFAULT__DISTANCE_RADIUS;
		}
		int pageParam = 1;
		if(CommonUtil.convertStringToInt(searchRequestParameters.getPage()) >1){
			pageParam = CommonUtil.convertStringToInt(searchRequestParameters.getPage());
		}
		List businesses = this.searchBusiness(keyword, address, Double.parseDouble(distance));
		SearchBusinessAggregateData searchBusinessAggregateData = parseIntoJavaBean(businesses, pageParam);
		if(searchBusinessAggregateData.getSearchReport() !=null && keyword !=null){
			searchBusinessAggregateData.getSearchReport().setKeyword(keyword);
		}
		
		searchBusinessAggregateData.getSearchReport().setCurrentPage(pageParam);
		return searchBusinessAggregateData;
		
	}
	
	public SearchBusinessAggregateData parseIntoJavaBean(List modelObject, int pageParam) throws ApiException{
		
		List<DBObject> dbObjectList = (List<DBObject>) modelObject;
		SearchBusinessAggregateData searchBusinessAggregateData = null;
		
		int recordsPerPage = ApplicationConstant.BUSINESS_RECORDS_PER_PAGE;
		
		//this index is used to calculate the index to retrive data for pagination
		int pageIndex = recordsPerPage * (pageParam -1);
		
		SearchBusiness searchBusiness;
		List<SearchBusiness> searchBusinesses = null;
		int totalRecords = 0;
			
		if(dbObjectList !=null && dbObjectList.size()>0 && pageIndex <= dbObjectList.size()){
			totalRecords = dbObjectList.size();
			
			searchBusinesses = new ArrayList<SearchBusiness>();
			for(int i=pageIndex; i<=pageIndex+recordsPerPage; i++){
				
				DBObject dbObject = dbObjectList.get(i);
				int record = 1;
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
				
				if(dbObject.get("distance") != null){
					//rounding logic
					distance= Math.round(distance * 100.0) / 100.0;
					searchBusiness.setDistance(Double.toString(distance));
				}
	//			searchBusiness.setCuisine(dbObject.get("features").toString());
	//			searchBusiness.setWorkingHours(dbObject.get("working hours").toString());
				searchBusinesses.add(searchBusiness);
				//logic for pagination( if number of index == recordLimit means record limit perpage is over
				if((i+1) >= totalRecords || searchBusinesses.size() == recordsPerPage){
					break;
				}
			}
		}	
		searchBusinessAggregateData = new SearchBusinessAggregateData();
		SearchReport searchReport = new SearchReport();
		searchBusinessAggregateData.setSearchBusinesses(searchBusinesses);
		//logic for pagination( if number of records > recordLimit means one more page can be retrieved and next page pagination should be shown on UI
		searchReport.setRecordsPerPage(ApplicationConstant.BUSINESS_RECORDS_PER_PAGE);
		searchReport.setTotalRecords(totalRecords);
		searchBusinessAggregateData.setSearchReport(searchReport);
		return searchBusinessAggregateData;
	}
	
	/**
	 * This is the main method returns the list of business based on the address and keyword if passed 
	 * It calls 2 dao methods
	 * 1. get list of all business near the lang and lat of the address
	 * 2. get list of business contains the keyword in above list of biz 
	 * and then filter the records which doesnt have the keywords 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List searchBusiness(String keyword, String address, double distance) {
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
		//if no keyword passed then businessByDistance list will be returned which the search by address only
		else{
			return businessByDistance;
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
