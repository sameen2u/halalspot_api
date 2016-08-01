package com.halal.sa.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.halal.sa.common.ApplicationConstant;
import com.halal.sa.common.CommonUtil;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.ErrorConstants;
import com.halal.sa.data.dao.impl.MyAccountDaoImpl;

@Component
@SuppressWarnings("unused")
public class ThirdPartyService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ThirdPartyService.class);
	
	protected RestTemplate restTemplate;
	
	ThirdPartyService (){
		restTemplate = new RestTemplate();
	}
	
	/**
	 * This method provides location map which contains lng and lat as key values of Longitude and Latitude for the provided address
	 * @param address
	 * @return
	 * @throws ApiException 
	 */
	public Map getLongiLatitude(String address) throws ApiException{
		//format address in google api search compatible format
		address = address.replaceAll(" ", "+");
		if(address.contains(",")){
			address = address.replaceAll(",", "");
		}
		Map<String,Object> location= new HashMap<String, Object>();
		String url = ApplicationConstant.GOOGLE_GEO_API_ENDPOINT+"?address="+address;
		LOGGER.debug("Google api url for getting lat & lng : "+url);
		ResponseEntity<String> response = callHttpGetMethod(url);
		Map jsonMap = CommonUtil.convertJsonStringToMap(response.getBody());
		if(jsonMap.get("status").equals("OK")){
			List<Map> results = (List) jsonMap.get("results");
			for(Map result : results){
				if(result.containsKey("address_components")){
					List<Map> addressComponents= (List<Map>) result.get("address_components");
					if(addressComponents!=null && addressComponents.size()>0){
						setCountryAndLocality(addressComponents, location);
//						location.put("locality", locality);
					}
				}
				if(result.containsKey("geometry")){
					Map geometry= (Map) result.get("geometry");
					location.put("coordinates", geometry.get("location"));
					break;
				}
			}
		}
		return location;
	}
	
	/*
	 * This method returns locality from the response of the google api 
	 */
	private Map<String,Object> setCountryAndLocality(List<Map> addressComponents, Map<String,Object> location) throws ApiException{
		String locality = null;
		String country = null;
		for(Map locationMap: addressComponents){
			List types = (List) locationMap.get("types");
			if(types.contains("country")){
				country = (String) locationMap.get("long_name");
				location.put("country", country);
			}
			if(types.contains("sublocality_level_1")){
				locality = (String) locationMap.get("long_name");
				location.put("locality", locality);
			}
		}
		return location;
	}

	/**
	 * This method calls get method of the third party api
	 * @param url
	 * @return
	 * @throws ApiException 
	 * @throws URISyntaxException 
	 * @throws RestClientException 
	 */
	private ResponseEntity<String> callHttpGetMethod(String url) throws ApiException{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//set entity
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<String> response;
		try {
			response = restTemplate.exchange(new URI(url), HttpMethod.GET, entity, String.class);
			return response;
		} catch (RestClientException e) {
			LOGGER.error("RestClientException - ", e);
			throw new ApiException(ErrorConstants.ERR_GOOGLE_API_HTTP_HOST_CONNECT, e);
		} catch (Exception e) {
			LOGGER.error("Exception in callHttpGetMethod() for Google API call - ", e);
			throw new ApiException(ErrorConstants.ERR_GOOGLE_API_HTTP_EXCEPTION, e);
		}
		
	}
}
