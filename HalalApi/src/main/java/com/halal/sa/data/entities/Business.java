package com.halal.sa.data.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="business")
public class Business implements Serializable{
	
	@Id
	private String id;
	
	private int profile_id;
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String category;
	private String serviceType;
	private String description;
	@Valid
	private Address address;
	private String imageIconUrl;
	private String phone;
	//it should be mandatory
	private String halalAuthenticity;
	private Boolean alchoholServed;
	private String email;
	private String website;
	private String status;
	private String userEmail;
	private String menuPresent;
	private String menuUrls;
	private Double avgRating;
	private String googlePlaceId;
	private Double googleRating;
	private int googleReviewCount;
	private String topGoogleReviews;
	private Double yelpRating;
	private int yelpReviewCount;
	private String topYelpReviews;
	private Double greatSchoolRating;
	private int greatSchoolReviewCount;
	private String topGreatSchoolReviews;
	private Double facebookRating;
	private int facebookReviewCount;
	private String topFacebookReviews;
	private String facebookPage;
	private String twitterPage;
	private String otherInfo;
	private String workingHours;
	private String features;	
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;	
	
	
	public String getImageIconUrl() {
		return imageIconUrl;
	}
	public void setImageIconUrl(String imageIconUrl) {
		this.imageIconUrl = imageIconUrl;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getGoogleReviewCount() {
		return googleReviewCount;
	}
	public void setGoogleReviewCount(int googleReviewCount) {
		this.googleReviewCount = googleReviewCount;
	}
	public Double getAvgRating() {
		return avgRating;
	}
	public String getGooglePlaceId() {
		return googlePlaceId;
	}
	public void setGooglePlaceId(String googlePlaceId) {
		this.googlePlaceId = googlePlaceId;
	}
	public int getYelpReviewCount() {
		return yelpReviewCount;
	}
	public void setYelpReviewCount(int yelpReviewCount) {
		this.yelpReviewCount = yelpReviewCount;
	}
	public int getGreatSchoolReviewCount() {
		return greatSchoolReviewCount;
	}
	public void setGreatSchoolReviewCount(int greatSchoolReviewCount) {
		this.greatSchoolReviewCount = greatSchoolReviewCount;
	}
	public int getFacebookReviewCount() {
		return facebookReviewCount;
	}
	public void setFacebookReviewCount(int facebookReviewCount) {
		this.facebookReviewCount = facebookReviewCount;
	}
	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}
	public Double getGoogleRating() {
		return googleRating;
	}
	public void setGoogleRating(Double googleRating) {
		this.googleRating = googleRating;
	}
	public Double getYelpRating() {
		return yelpRating;
	}
	public void setYelpRating(Double yelpRating) {
		this.yelpRating = yelpRating;
	}
	public Double getGreatSchoolRating() {
		return greatSchoolRating;
	}
	public void setGreatSchoolRating(Double greatSchoolRating) {
		this.greatSchoolRating = greatSchoolRating;
	}
	public Double getFacebookRating() {
		return facebookRating;
	}
	public void setFacebookRating(Double facebookRating) {
		this.facebookRating = facebookRating;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
	public String getGooglePLaceId() {
		return googlePlaceId;
	}
	public void setGooglePLaceId(String googlePLaceId) {
		this.googlePlaceId = googlePLaceId;
	}
	public String getTopGoogleReviews() {
		return topGoogleReviews;
	}
	public void setTopGoogleReviews(String topGoogleReviews) {
		this.topGoogleReviews = topGoogleReviews;
	}
	public String getTopYelpReviews() {
		return topYelpReviews;
	}
	public void setTopYelpReviews(String topYelpReviews) {
		this.topYelpReviews = topYelpReviews;
	}
	public String getTopGreatSchoolReviews() {
		return topGreatSchoolReviews;
	}
	public void setTopGreatSchoolReviews(String topGreatSchoolReviews) {
		this.topGreatSchoolReviews = topGreatSchoolReviews;
	}
	public String getTopFacebookReviews() {
		return topFacebookReviews;
	}
	public void setTopFacebookReviews(String topFacebookReviews) {
		this.topFacebookReviews = topFacebookReviews;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public int getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
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
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
	public String getFacility() {
		return features;
	}
	public void setFacility(String facility) {
		this.features = facility;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getAuthenticity() {
		return halalAuthenticity;
	}
	public void setAuthenticity(String authenticity) {
		this.halalAuthenticity = authenticity;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
	
}
