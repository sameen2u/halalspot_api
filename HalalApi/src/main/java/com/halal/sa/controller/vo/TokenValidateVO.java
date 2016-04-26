package com.halal.sa.controller.vo;

public class TokenValidateVO {
	
	private int userId;
	private String sessionToken;
	private String activityToken;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public String getActivityToken() {
		return activityToken;
	}
	public void setActivityToken(String activityToken) {
		this.activityToken = activityToken;
	}
	
}
