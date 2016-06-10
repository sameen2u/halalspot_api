package com.halal.sa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
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
import com.halal.sa.data.dao.impl.MyAccountDaoImpl;

@Component
@SuppressWarnings("unused")
public class ThirdPartyService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MyAccountDaoImpl.class);
	
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
		Map<String,Object> location= new HashMap<String, Object>();
		String response = callHttpGetMethod(ApplicationConstant.GOOGLE_GEO_API_ENDPOINT+"?address="+address);
		response = StringUtils.substringAfter(response, "<200 OK,");
		response = StringUtils.substringBefore(response, ",{Content-Type=");
		Map jsonMap = CommonUtil.convertJsonStringToMap(response);
		if(jsonMap.get("status").equals("OK")){
			List<Map> results = (List) jsonMap.get("results");
			for(Map result : results){
				if(result.containsKey("address_components")){
					List<Map> addressComponents= (List<Map>) result.get("address_components");
					if(addressComponents!=null && addressComponents.size()>0){
						String locality = getLocality(addressComponents);
						location.put("locality", locality);
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
	private String getLocality(List<Map> addressComponents){
		String locality = null;
		for(Map locationMap: addressComponents){
			List types = (List) locationMap.get("types");
			if(types.contains("sublocality_level_1")){
				locality = (String) locationMap.get("long_name");
				break;
			}
		}
		return locality;
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
