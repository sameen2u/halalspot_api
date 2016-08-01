package com.halal.sa.processor.searchbusiness;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.halal.sa.common.ApplicationConstant;
import com.halal.sa.common.CommonUtil;
import com.halal.sa.controller.vo.BusinessVO;
import com.halal.sa.controller.vo.KeywordSearchVO;
import com.halal.sa.controller.vo.response.SearchBusiness;
import com.halal.sa.controller.vo.response.SearchReport;
import com.halal.sa.core.AbstractProcessor;
import com.halal.sa.core.AggregateData;
import com.halal.sa.core.RequestParameters;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;
import com.halal.sa.core.exception.ErrorConstants;
import com.halal.sa.core.exception.ErrorConstants;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.dao.RestaurantDao;
import com.halal.sa.data.dao.SearchBusinessDao;
import com.halal.sa.data.dao.impl.BusinessDaoImpl;
import com.halal.sa.data.entities.Address;
import com.halal.sa.data.entities.Business;
import com.halal.sa.data.entities.RestaurantInfo;
import com.halal.sa.service.ThirdPartyService;
import com.mongodb.DBObject;

@Component
public class SearchBusinessProcessor extends AbstractProcessor{
	
	private final Logger LOGGER = LoggerFactory.getLogger(SearchBusinessProcessor.class);
	
	String KEYWORD=null;
	int PAGE=0;
	
	@Autowired
	BusinessDaoImpl businessDaoImpl;
	
	@Autowired
	ThirdPartyService thirdPartyService;
	
	@Autowired
	SearchBusinessDao searchBusinessDao; 
	
	@Autowired
	RestaurantDao restaurantDao;

	@Override
	public AggregateData retrieveData(RequestParameters requestParameters)
			throws BadRequestException, ApiException {
		SearchRequestParameters searchRequestParameters = (SearchRequestParameters) requestParameters;
		SearchBusinessAggregateData searchBusinessAggregateData = searchProcessor(searchRequestParameters);
		return searchBusinessAggregateData;
	}
	
	/**
	 * This the main Search method returning the response based on Api request input
	 * @param apiRequest
	 * @return
	 * @throws ApiException
	 * @throws BadRequestException 
	 * @throws NumberFormatException 
	 */
	public SearchBusinessAggregateData searchProcessor(SearchRequestParameters searchRequestParameters) throws BadRequestException, ApiException{
		String keyword = searchRequestParameters.getKeyword();
		String address = searchRequestParameters.getAddress();
		String distance = searchRequestParameters.getRadius();
		String lat = searchRequestParameters.getLattitude();
		String lng = searchRequestParameters.getLongitude();
		
		if( distance == null || Integer.parseInt(distance) < Integer.parseInt(ApplicationConstant.BUSINESS_DEFAULT__DISTANCE_RADIUS)){
			distance = ApplicationConstant.BUSINESS_DEFAULT__DISTANCE_RADIUS;
		}
		int pageParam = 1;
		if(CommonUtil.convertStringToInt(searchRequestParameters.getPage()) >1){
			pageParam = CommonUtil.convertStringToInt(searchRequestParameters.getPage());
		}
		if(StringUtils.isNotBlank(address) || StringUtils.isNotBlank(lat) && StringUtils.isNotBlank(lng)){
			LOGGER.info("searchProcessor method searching for address - "+address+", distance - "+distance+", keyword - "+keyword);
			List businesses = this.searchBusinessService(keyword, address, Double.parseDouble(distance), lat, lng);
			SearchBusinessAggregateData searchBusinessAggregateData = parseIntoJavaBean(businesses, pageParam);
			if(searchBusinessAggregateData.getSearchReport() !=null && keyword !=null){
				searchBusinessAggregateData.getSearchReport().setKeyword(keyword);
			}
			
			searchBusinessAggregateData.getSearchReport().setCurrentPage(pageParam);
			return searchBusinessAggregateData;
		}
		else{
			String errorMsg = ErrorConstants.ERRORDESC_MANDATORY_PARAM_MISSING;
			LOGGER.error(ErrorConstants.ERRCODE_BAD_REQUEST, errorMsg);
			throw new BadRequestException(ErrorConstants.ERRCODE_BAD_REQUEST, errorMsg);
		}
	}
	
	/**
	 * This method will set the data in aggregate data object from the DB response
	 * @param modelObject
	 * @param pageParam
	 * @return
	 * @throws ApiException
	 */
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
				if(dbObject.get("profile_id") != null){
					searchBusiness.setProfile_id((int)dbObject.get("profile_id"));
				}
				String profileUri = constructProfileUrl(dbObject, address);
				if(profileUri !=null){
					searchBusiness.setProfileUri(profileUri);
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
	 * @throws ApiException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List searchBusinessService(String keyword, String address, double distance, String lat, String lng) throws ApiException {
		List businessIds = null;
		List<DBObject> businessByDistance = null;
		List<DBObject> businessByKeyword = null;
		List<DBObject> resultBusiness= Collections.emptyList();
		double longitude = 0;
		double latitude = 0;
		if(StringUtils.isNotBlank(lat) && StringUtils.isNotBlank(lng)){
			latitude = Double.parseDouble(lat);
			longitude = Double.parseDouble(lng);
		}
		else{
			Map<String,Object> map = thirdPartyService.getLongiLatitude(address);
			if(map !=null && map.size() >0){
				if(!StringUtils.equals((String) map.get("country"), "India")){
					return resultBusiness;
				}
				Map coordinate = (Map)map.get("coordinates");
				longitude = (double) coordinate.get("lng");
				latitude = (double) coordinate.get("lat");
			}
			LOGGER.error("Google Api not returned the coordinates");
		}
		
		businessByDistance= businessDaoImpl.searchBusinessByLocation(longitude, latitude, distance);
		
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
		
		LOGGER.info("searchBusinessService method searching for address - "+address+", distance - "+distance+", keyword - "+keyword+", SearchResult count - "+resultBusiness.size());
		return resultBusiness;		
	}
	
	/*
	 * This method will return full business data for the profile id passed
	 */
	public BusinessVO searchSingleBusiness(String city, int businessProfileId) throws ApiException{
		Business business = searchBusinessDao.findByBusinessCodeAndProfileId(city, businessProfileId);
		if(business == null){
			throw new ApiException(ErrorConstants.ERRORDESC_DATA_NOT_FOUND_DB);
		}
		/*try {
			BeanUtils.copyProperties(businessVO, business);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return populateBusinessVO(business);		
	}
	
	public String constructProfileUrl(DBObject dbObject, Address address){
		String name = (String) dbObject.get("name").toString().replace(" ", "-");
		String city = address.getCity();
		String locality = "";
		if(!StringUtils.isBlank(address.getLocality())){
			locality = "-"+address.getLocality().toLowerCase().replace(" ", "-");
		}
		String profileId = dbObject.get("profile_id").toString();
		
		String profileUri = "/"+city.toLowerCase()+"/"+name.toLowerCase()+locality+"/"+profileId;
		return profileUri;
	}
	
	private BusinessVO populateBusinessVO(Business business){
		BusinessVO businessVO = new BusinessVO();
		if(StringUtils.isNotBlank(business.getId())){
			businessVO.setId(business.getId());
		}
		if(StringUtils.isNotBlank(business.getName())){
			businessVO.setName(business.getName());
		}
		if(business.getAddress() != null){
			businessVO.setAddress(business.getAddress());
		}
		if(business.getPhone() > 0){
			businessVO.setPhone(business.getPhone());
		}
		if(StringUtils.isNotBlank(business.getEmail())){
			businessVO.setEmail(business.getEmail());
		}
		if(StringUtils.isNotBlank(business.getOwnerEmail())){
			businessVO.setOwnerEmail(business.getOwnerEmail());
		}
		if(StringUtils.isNotBlank(business.getStatus())){
			businessVO.setStatus(business.getStatus());
		}
		if(StringUtils.isNotBlank(business.getWebsite())){
			businessVO.setWebsite(business.getWebsite());
		}
		if(StringUtils.isNotBlank(business.getStatus())){
			businessVO.setStatus(business.getStatus());
		}
		if(business.getCuisine() != null){
			businessVO.setCuisine(business.getCuisine());
		}
		if(StringUtils.isNotBlank(business.getFacebookPage())){
			businessVO.setFacebookPage(business.getFacebookPage());
		}
		if(StringUtils.isNotBlank(business.getTwitterPage())){
			businessVO.setTwitterPage(business.getTwitterPage());
		}
		
		if(StringUtils.isNotBlank(business.getOtherInfo())){
			businessVO.setOtherInfo(business.getOtherInfo());
		}
		if(business.getLastUpdated() != null){
			businessVO.setLastUpdated(business.getLastUpdated());
		}
		
		this.populateWorkingHours(business, businessVO);
		this.populateFacilities(business, businessVO);
		
		if(StringUtils.isNotBlank(business.getAuthenticity())){
			businessVO.setAuthenticity(business.getAuthenticity());
		}
		if(StringUtils.isNotBlank(business.getCreatedBy())){
			businessVO.setCreatedBy(business.getCreatedBy());
		}
		if(StringUtils.isNotBlank(business.getUpdatedBy())){
			businessVO.setUpdatedBy(business.getUpdatedBy());
		}
		return businessVO;
	}
	
	/*
	 * This method populates working hours with respect to each day in map format{monday{open : 07:00, close : 8:00}, sunday:closed}}
	 */
	protected void populateWorkingHours(Business business, BusinessVO businessVO){ 
		Map<String, Object> workingHoursMap = null;
		if(StringUtils.isNotBlank(business.getWorkingHours())){
			workingHoursMap = new HashMap<String, Object>();
			//example string = "mon=07:00-21:00,tue=07:00-21:00"
			String weekHours = business.getWorkingHours();
			String[] arr = weekHours.split(",");
			for(String dayHours : arr){
				if(dayHours.contains("mon")){
					dayHours = dayHours.replace("mon=", "");
					if(StringUtils.equals(dayHours, "closed")){
						workingHoursMap.put("monday", dayHours);
						break;
					}
					
					String[] hours = dayHours.split("-");
					if(hours != null && hours.length >0){
						Map<String, String> monday = new HashMap<String, String>();
						monday.put("open", hours[0]);
						monday.put("close", hours[1]);
						workingHoursMap.put("monday", monday);
					}
				}
				if(dayHours.contains("tue")){
					dayHours = dayHours.replace("tue=", "");
					if(StringUtils.equals(dayHours, "closed")){
						workingHoursMap.put("tuesday", dayHours);
						break;
					}
					
					String[] hours = dayHours.split("-");
					if(hours != null && hours.length >0){
						Map<String, String> tuesday = new HashMap<String, String>();
						tuesday.put("open", hours[0]);
						tuesday.put("close", hours[1]);
						workingHoursMap.put("tuesday", tuesday);
					}
				}
				if(dayHours.contains("wed")){
					dayHours = dayHours.replace("wed=", "");
					if(StringUtils.equals(dayHours, "closed")){
						workingHoursMap.put("wednesday", dayHours);
						break;
					}
					
					String[] hours = dayHours.split("-");
					if(hours != null && hours.length >0){
						Map<String, String> wednesday = new HashMap<String, String>();
						wednesday.put("open", hours[0]);
						wednesday.put("close", hours[1]);
						workingHoursMap.put("wednesday", wednesday);
					}
				}
				if(dayHours.contains("thur")){
					dayHours = dayHours.replace("thur=", "");
					if(StringUtils.equals(dayHours, "closed")){
						workingHoursMap.put("thursday", dayHours);
						break;
					}
					
					String[] hours = dayHours.split("-");
					if(hours != null && hours.length >0){
						Map<String, String> thursday = new HashMap<String, String>();
						thursday.put("open", hours[0]);
						thursday.put("close", hours[1]);
						workingHoursMap.put("thursday", thursday);
					}
				}
				if(dayHours.contains("fri")){
					dayHours = dayHours.replace("fri=", "");
					String[] hours = dayHours.split("-");
					if(hours != null && hours.length >0){
						Map<String, String> friday = new HashMap<String, String>();
						friday.put("open", hours[0]);
						friday.put("close", hours[1]);
						workingHoursMap.put("friday", friday);
					}
				}
				if(dayHours.contains("sat")){
					dayHours = dayHours.replace("sat=", "");
					if(StringUtils.equals(dayHours, "closed")){
						workingHoursMap.put("saturday", dayHours);
						break;
					}
					String[] hours = dayHours.split("-");
					if(hours != null && hours.length >0){
						Map<String, String> saturday = new HashMap<String, String>();
						saturday.put("open", hours[0]);
						saturday.put("close", hours[1]);
						workingHoursMap.put("saturday", saturday);
					}
				}
				if(dayHours.contains("sun")){
					dayHours = dayHours.replace("sun=", "");
					if(StringUtils.equals(dayHours, "closed")){
						workingHoursMap.put("sunday", dayHours);
						break;
					}
					String[] hours = dayHours.split("-");
					if(hours != null && hours.length >0){
						Map<String, String> sunday = new HashMap<String, String>();
						sunday.put("open", hours[0]);
						sunday.put("close", hours[1]);
						workingHoursMap.put("sunday", sunday);
					}
				}
			}
		}
		businessVO.setWorkingHours(workingHoursMap);
	}
	
	
	private void populateFacilities(Business business, BusinessVO businessVO){
		Map<String, Boolean> facilitiesMap = null;
		if(StringUtils.isNotBlank(business.getFacility())){
			facilitiesMap = new HashMap<String, Boolean>();
			//"takeaway,mealCoupon,hooka,juiceCenter,creditcardAccepted,indoorSeating" 
			String facitiliesStr = business.getFacility();
			String[] arr = facitiliesStr.split(",");
			for(String facility: arr){
				facilitiesMap.put(facility, Boolean.TRUE);
			}
		
		}
		businessVO.setFacilities(facilitiesMap);
	}

	public KeywordSearchVO searchKeyword(String city, String keywordInitials) {
		List<RestaurantInfo> responseList = restaurantDao.findCuisineKeywords(keywordInitials);
		KeywordSearchVO keywordSearchVO = new KeywordSearchVO();
		keywordSearchVO.setCity(city);
		List<Map<String, String>> keywordList = new ArrayList<Map<String, String>>();
		Map<String, String> keywordMap = Collections.emptyMap(); 
		int sortIndex = 0;
		for(RestaurantInfo info: responseList){
			String keyword = info.getName();
			keywordMap = new HashMap<String, String>();
			keywordMap.put("keyword", keyword);
			keywordMap.put("type", info.getType());
			//this logic is to sort list by keywordinitail passed
			if(StringUtils.lowerCase(keyword).startsWith(keywordInitials)){
				keywordList.add(sortIndex, keywordMap);
				sortIndex++;
			}else{
				keywordList.add(keywordMap);
			}
		}
		keywordSearchVO.setKeywords(keywordList);
		return keywordSearchVO;
	}
}
