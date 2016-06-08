package com.halal.sa.processor.myaccount;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.halal.sa.controller.vo.response.SearchBusiness;
import com.halal.sa.controller.vo.response.SearchReport;
import com.halal.sa.core.AggregateData;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyAccountAggregateData implements AggregateData{
	
	private String userId;
	private String email;
	private String name;
	private String userActivityToken;
	private String sessionToken;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserActivityToken() {
		return userActivityToken;
	}
	public void setUserActivityToken(String userActivityToken) {
		this.userActivityToken = userActivityToken;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	
}
