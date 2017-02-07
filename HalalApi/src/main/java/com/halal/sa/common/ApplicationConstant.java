package com.halal.sa.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ApplicationConstant {
	
	public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("Application");
	
	public static final String GOOGLE_GEO_API_ENDPOINT = resourceBundle.getString("GOOGLE_GEO_API_ENDPOINT");
	
	public static final int BUSINESS_RECORDS_PER_PAGE = Integer.parseInt(resourceBundle.getString("BUSINESS_RECORDS_PER_PAGE"));
	public static final int BUSINESS_PAGINATION_EXTRA_RECORD = Integer.parseInt(resourceBundle.getString("BUSINESS_PAGINATION_EXTRA_RECORD"));
	public static final String BUSINESS_DEFAULT_DISTANCE_RADIUS = resourceBundle.getString("BUSINESS_DEFAULT_DISTANCE_RADIUS");
	
	public static final String MILES = "mi";
	public static final String KILOMETRES = "km";	
	public static final String CATEGORY_NAME_RESTAURANT = "Restaurants and Caters";
	public static final String CATEGORY_NAME_MOSQUE = "Mosques";
	public static final String CATEGORY_NAME_SCHOOL = "Islamic Schools";
	public static final String CATEGORY_NAME_STORE = "Islamic Clothing/Book Stores";
	

	public static final Map<String, String> DISTANCE_UNIT_MAP;
	public static final Map<String, String> CATEGORY_NAME_MAP;
	
	static{
		Map map = new HashMap<String, String>();
		map.put("united states", MILES);
		map.put("india", KILOMETRES);
		DISTANCE_UNIT_MAP = Collections.unmodifiableMap(map);
		
		
		Map map2 = new HashMap<String, String>();
		map2.put("restaurant", CATEGORY_NAME_RESTAURANT);
		map2.put("mosque", CATEGORY_NAME_MOSQUE);
		map2.put("school", CATEGORY_NAME_SCHOOL);
		map2.put("store", CATEGORY_NAME_STORE);
		CATEGORY_NAME_MAP = Collections.unmodifiableMap(map2);
	}


}
