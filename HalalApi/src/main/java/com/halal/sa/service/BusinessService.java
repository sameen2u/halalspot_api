package com.halal.sa.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.util.ObjectUtils;

import com.halal.sa.common.error.ErrorResponseGenerator;
import com.halal.sa.data.dao.BusinessDao;
import com.halal.sa.data.dao.impl.AccountDaoImpl;
import com.halal.sa.data.dao.impl.BusinessDaoImpl;
import com.halal.sa.data.entities.Address;
import com.halal.sa.data.entities.Business;
import com.halal.sa.data.entities.Location;

@Service
public class BusinessService extends BaseService{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AccountDaoImpl.class);
	
	@Autowired
	BusinessDao businessDaoImpl;
	
	@Autowired
	ThirdPartyService thirdPartyService;
	
	/**
	 * This service method will add the business object to the Business Document in DB 
	 * @param business
	 * @param request
	 * @return
	 */
	public ResponseEntity<Object> registerBusiness(Business business, HttpServletRequest request){
		if(!ObjectUtils.isEmpty(business) && validate(business)){
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
//			business.setAddress(address);
			businessDaoImpl.addBusinessInfo(business);
			return processResponseEntity(processResponse(business, request), HttpStatus.OK);
		}
		return processResponseEntity(ErrorResponseGenerator.INCOMPLETE_DATA_RESPONSE, HttpStatus.OK);
	}

	@Override
	protected Object processResponse(Object model, HttpServletRequest request) {
		Business business = (Business) model;
		Map<String, Object> respMap = new HashMap<String, Object>();
		if(request.getAttribute("method").equals("register")){
			respMap.put("restaurantName", business.getName());
			respMap.put("email", business.getEmail());
		}
		return respMap;
	}

	@Override
	protected boolean validate(Object model) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
	    Set violations = validator.validate(model);
	    if(violations.isEmpty()){
	    	return true;
	    }
		return false;
	}
	
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
		address = address+" India";
		return address = address.replaceAll(" ", "+");
	}
}
