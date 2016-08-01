package com.halal.sa.controller.vo;

import java.util.List;
import java.util.Map;

public class KeywordSearchVO {
	
	private String city;
	private List<Map<String, String>> keywords;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Map<String, String>> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<Map<String, String>> keywords) {
		this.keywords = keywords;
	}
	
	
}
