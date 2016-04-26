package com.halal.sa.controller.vo.response;

import java.util.List;
import java.util.Map;

import com.halal.sa.data.entities.Address;

public class BusinessSearchResult {
	
	private String name;
	private String description;
	private List<String> cuisine;
	private Address address;
	private int phone;
	private String status;
	private Map<String, String> workingHours;
	private String authenticity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getCuisine() {
		return cuisine;
	}
	public void setCuisine(List<String> cuisine) {
		this.cuisine = cuisine;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, String> getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(Map<String, String> workingHours) {
		this.workingHours = workingHours;
	}
	public String getAuthenticity() {
		return authenticity;
	}
	public void setAuthenticity(String authenticity) {
		this.authenticity = authenticity;
	}
}
