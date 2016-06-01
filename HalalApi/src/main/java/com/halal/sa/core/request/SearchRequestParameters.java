package com.halal.sa.core.request;

import com.halal.sa.core.RequestParameters;

public class SearchRequestParameters implements RequestParameters{
	
	private String keyword;
	private String address; 
	private String radius; 
	private String page;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	} 

}
