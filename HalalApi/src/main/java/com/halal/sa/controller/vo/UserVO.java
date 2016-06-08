package com.halal.sa.controller.vo;

import org.apache.commons.collections.map.MultiValueMap;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by sameen
 * Date: 03/10/2016
 * Copyright HalalSpot.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO{

	private String fullName;
	private String email;
	private String password;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
