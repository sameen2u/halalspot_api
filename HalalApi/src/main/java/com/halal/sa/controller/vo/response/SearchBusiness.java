package com.halal.sa.controller.vo.response;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.halal.sa.data.entities.Address;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchBusiness {
	
	private String name;
	private String category;
	private int profile_id;
	private String profileUri;
	private String description;
	//this is cuisine for restaurant, grades in school for school, etc
	private String serviceType;
	private String imageIconUrl;
	private Double avgRating;
	private Integer totalReviewCount;
	private Address address;
	private String phone;
	private String status;
	private Boolean cateringServed;
	private Map<String, String> workingHours;
	private String authenticity;
	private String distance; 
	private String distanceUnit;
	
	public String getImageIconUrl() {
		return imageIconUrl;
	}
	public void setImageIconUrl(String imageIconUrl) {
		this.imageIconUrl = imageIconUrl;
	}
	public String getDistanceUnit() {
		return distanceUnit;
	}
	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Boolean getCateringServed() {
		return cateringServed;
	}
	public void setCateringServed(Boolean cateringServed) {
		this.cateringServed = cateringServed;
	}
	public Double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}
	public Integer getTotalReviewCount() {
		return totalReviewCount;
	}
	public void setTotalReviewCount(Integer totalReviewCount) {
		this.totalReviewCount = totalReviewCount;
	}
	public String getProfileUri() {
		return profileUri;
	}
	public void setProfileUri(String profileUrl) {
		this.profileUri = profileUrl;
	}
	public int getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
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

	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
