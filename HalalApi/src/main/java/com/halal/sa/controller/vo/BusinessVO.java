package com.halal.sa.controller.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.halal.sa.data.entities.Address;

public class BusinessVO {
	

	private String id;
	private Integer profile_id;
	private String name;
	private String category;
	private String serviceType;
	private String description;
	private Address address;
	private String imageIconUrl;
	private String phone;
	private String halalAuthenticity;
	private Boolean alchoholServed;
	private String email;
	private String website;
	private String status;
	private String userEmail;
	private String menuPresent;
	private String menuUrls;
	private Double avgRating;
	private Integer totalReviewCount;
	private String googlePlaceId;
	private Double googleRating;
	private Integer googleReviewCount;
	private String topGoogleReviews;
	private Double yelpRating;
	private Integer yelpReviewCount;
	private String topYelpReviews;
	private Double greatSchoolRating;
	private Integer greatSchoolReviewCount;
	private String topGreatSchoolReviews;
	private Double facebookRating;
	private Integer facebookReviewCount;
	private String topFacebookReviews;
	private String facebookPage;
	private String twitterPage;
	private String otherInfo;
	private String features;	
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;	
	private Map<String, Object> workingHours;
	private Map<String, Boolean> facilities;
	
	
	public Integer getTotalReviewCount() {
		return totalReviewCount;
	}
	public void setTotalReviewCount(Integer totalReviewCount) {
		this.totalReviewCount = totalReviewCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(Integer profile_id) {
		this.profile_id = profile_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getImageIconUrl() {
		return imageIconUrl;
	}
	public void setImageIconUrl(String imageIconUrl) {
		this.imageIconUrl = imageIconUrl;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHalalAuthenticity() {
		return halalAuthenticity;
	}
	public void setHalalAuthenticity(String halalAuthenticity) {
		this.halalAuthenticity = halalAuthenticity;
	}
	public Boolean getAlchoholServed() {
		return alchoholServed;
	}
	public void setAlchoholServed(Boolean alchoholServed) {
		this.alchoholServed = alchoholServed;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getMenuPresent() {
		return menuPresent;
	}
	public void setMenuPresent(String menuPresent) {
		this.menuPresent = menuPresent;
	}
	public String getMenuUrls() {
		return menuUrls;
	}
	public void setMenuUrls(String menuUrls) {
		this.menuUrls = menuUrls;
	}
	public Double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}
	public String getGooglePlaceId() {
		return googlePlaceId;
	}
	public void setGooglePlaceId(String googlePlaceId) {
		this.googlePlaceId = googlePlaceId;
	}
	public Double getGoogleRating() {
		return googleRating;
	}
	public void setGoogleRating(Double googleRating) {
		this.googleRating = googleRating;
	}
	public Integer getGoogleReviewCount() {
		return googleReviewCount;
	}
	public void setGoogleReviewCount(Integer googleReviewCount) {
		this.googleReviewCount = googleReviewCount;
	}
	public String getTopGoogleReviews() {
		return topGoogleReviews;
	}
	public void setTopGoogleReviews(String topGoogleReviews) {
		this.topGoogleReviews = topGoogleReviews;
	}
	public Double getYelpRating() {
		return yelpRating;
	}
	public void setYelpRating(Double yelpRating) {
		this.yelpRating = yelpRating;
	}
	public Integer getYelpReviewCount() {
		return yelpReviewCount;
	}
	public void setYelpReviewCount(Integer yelpReviewCount) {
		this.yelpReviewCount = yelpReviewCount;
	}
	public String getTopYelpReviews() {
		return topYelpReviews;
	}
	public void setTopYelpReviews(String topYelpReviews) {
		this.topYelpReviews = topYelpReviews;
	}
	public Double getGreatSchoolRating() {
		return greatSchoolRating;
	}
	public void setGreatSchoolRating(Double greatSchoolRating) {
		this.greatSchoolRating = greatSchoolRating;
	}
	public Integer getGreatSchoolReviewCount() {
		return greatSchoolReviewCount;
	}
	public void setGreatSchoolReviewCount(Integer greatSchoolReviewCount) {
		this.greatSchoolReviewCount = greatSchoolReviewCount;
	}
	public String getTopGreatSchoolReviews() {
		return topGreatSchoolReviews;
	}
	public void setTopGreatSchoolReviews(String topGreatSchoolReviews) {
		this.topGreatSchoolReviews = topGreatSchoolReviews;
	}
	public Double getFacebookRating() {
		return facebookRating;
	}
	public void setFacebookRating(Double facebookRating) {
		this.facebookRating = facebookRating;
	}
	public Integer getFacebookReviewCount() {
		return facebookReviewCount;
	}
	public void setFacebookReviewCount(Integer facebookReviewCount) {
		this.facebookReviewCount = facebookReviewCount;
	}
	public String getTopFacebookReviews() {
		return topFacebookReviews;
	}
	public void setTopFacebookReviews(String topFacebookReviews) {
		this.topFacebookReviews = topFacebookReviews;
	}
	public String getFacebookPage() {
		return facebookPage;
	}
	public void setFacebookPage(String facebookPage) {
		this.facebookPage = facebookPage;
	}
	public String getTwitterPage() {
		return twitterPage;
	}
	public void setTwitterPage(String twitterPage) {
		this.twitterPage = twitterPage;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Map<String, Object> getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(Map<String, Object> workingHours) {
		this.workingHours = workingHours;
	}
	public Map<String, Boolean> getFacilities() {
		return facilities;
	}
	public void setFacilities(Map<String, Boolean> facilities) {
		this.facilities = facilities;
	}
}
