package com.halal.sa.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.dao.BusinessDao;
import com.halal.sa.data.entities.Address;
import com.halal.sa.data.entities.Business;
import com.halal.sa.data.entities.Location;

@Service
@SuppressWarnings("rawtypes")
public class BusinessService extends BaseService{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);
	
	@Autowired
	BusinessDao businessDaoImpl;
	
	@Autowired
	ThirdPartyService thirdPartyService;
	
	@Autowired
	DBCounterService counterService;
	
	/**
	 * This service method will add the business object to the Business Document in DB 
	 * @param business
	 * @param request
	 * @return
	 * @throws ApiException 
	 */
	public ResponseEntity<Object> registerBusiness(Business business, HttpServletRequest request) throws ApiException{
		Map errorMap = validate(business);
		if(business != null && errorMap.isEmpty()){
			LOGGER.info("Adding the Business to the DB - "+business.getName());
			//updating Longitude and Latitude for the address in the DB
			String BizAddress = constructGoogleApiAddressInUrl(business);
			Map locationMap = thirdPartyService.getLongiLatitude(BizAddress);
			String locality=null;
			Map coordinateMap = Collections.emptyMap();
			double[] coordinates = null;
			if(locationMap!=null){
				locality = (String) locationMap.get("locality");
				coordinateMap = (Map) locationMap.get("coordinates");
			}
			
			if(coordinateMap!=null){
				coordinates = new double[]{(double) coordinateMap.get("lng"),(double) coordinateMap.get("lat")};
			}
			Address address = business.getAddress();
			Location location = new Location();
			location.setType("Point");
			location.setCoordinates(coordinates);
			address.setLocality(locality);
			address.setLocation(location);
			business.setCreatedDate(new Date());
			business.setCreatedBy(business.getUserEmail());
			
			//getting seq id from counters table for the passed collection name
			int profileId = counterService.getNextSequence("business");
			business.setProfile_id(profileId);
			
			businessDaoImpl.addBusinessInfo(business);
			return processResponseEntity(processResponse(business, request), HttpStatus.OK);
		}
		
		return processResponseEntity(errorMap, HttpStatus.BAD_REQUEST);
	}

	/*
	 * This process response for adding the business
	 */
	@Override
	protected Object processResponse(Object model, HttpServletRequest request) {
		Business business = (Business) model;
		Map<String, Object> respMap = new HashMap<String, Object>();
		if(request.getAttribute("method").equals("register")){
			respMap.put("restaurantName", business.getName());
			respMap.put("email", business.getEmail());
			LOGGER.info("Sucessfully Added the Business - "+business.getName());
		}
		return respMap;
	}

	
	/**
	 * This method will validate the pojo
	 */
	
	
	@SuppressWarnings("unchecked")
	protected Map validate(Object model) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Map errorMap = Collections.emptyMap();
	    Set violations = validator.validate(model);
	    if(violations.isEmpty()){
	    	return errorMap;
	    }
	    else{
	    	errorMap = new LinkedHashMap<String,String>();
	    	errorMap.put("errorCode", HttpStatus.BAD_REQUEST);
	    	Iterator<ConstraintViolation> iterator = violations.iterator();
	    	while(iterator.hasNext()){
	    		ConstraintViolation cv = iterator.next();
	    		errorMap.put(cv.getPropertyPath(), cv.getMessage());
	    		LOGGER.error("Error count in validating POST JSON object - missing parameter :"+cv.getPropertyPath());
	    	}
	    	
	    	return errorMap;
	    }
	}
	
	/*
	 * This method returns address formatted in google patern from business object
	 */
	protected String constructGoogleApiAddressInUrl(Business business){
		String address = null;
		if(StringUtils.isNotBlank(business.getAddress().getStreetAddress())){
			address = business.getAddress().getStreetAddress().trim();
		}
		if(StringUtils.isNotBlank(business.getAddress().getCity())){
			address = address+" "+business.getAddress().getCity();
		}
		if(business.getAddress().getPincode() >=100000 && business.getAddress().getPincode() <= 999999){
			address = address+" "+Integer.toString(business.getAddress().getPincode());
		}
		return address = address.replaceAll(" ", "+");
	}

	@Override
	protected SearchRequestParameters validate(ApiRequest apiRequest)
			throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}
}
