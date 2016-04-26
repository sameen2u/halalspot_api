package com.halal.sa.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.halal.sa.common.ApplicationConstant;
import com.halal.sa.common.CommonUtil;
import com.halal.sa.data.dao.impl.AccountDaoImpl;

@Component
@SuppressWarnings("unused")
public class ThirdPartyService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AccountDaoImpl.class);
	
	protected RestTemplate restTemplate;
	
	ThirdPartyService (){
		restTemplate = new RestTemplate();
	}
	
	/**
	 * This method provides location map which contains lng and lat as key values of Longitude and Latitude for the provided address
	 * @param address
	 * @return
	 */
	public Map getLongiLatitude(String address){
		Map<String,String> location= null;
		String response = callHttpGetMethod(ApplicationConstant.GOOGLE_GEO_API_ENDPOINT+"?address="+address);
		response = StringUtils.substringAfter(response, "<200 OK,");
		response = StringUtils.substringBefore(response, ",{Content-Type=");
		Map jsonMap = CommonUtil.convertJsonStringToMap(response);
		if(jsonMap.get("status").equals("OK")){
			List<Map> results = (List) jsonMap.get("results");
			for(Map result : results){
				if(result.containsKey("geometry")){
					Map geometry= (Map) result.get("geometry");
					return (Map) geometry.get("location");
				}
			}
		}
		return null;
	}

	/**
	 * This method calls get method of the third party api 
	 * @param url
	 * @return
	 */
	private String callHttpGetMethod(String url){
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response.toString();
	}
}
