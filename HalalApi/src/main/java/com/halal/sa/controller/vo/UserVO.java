package com.halal.sa.controller.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by sameen
 * Date: 03/10/2016
 * Copyright HalalSpot.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO {

	private String fullName;
	private String email;
	private String password;
	private String city;
	private int phone;
	
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
