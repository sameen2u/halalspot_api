package com.halal.sa.data.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="business")
public class Business implements Serializable{
	
	@Id
	private String id;
	
	@NotNull
	private String name;
	private String description;
	private List<String> cuisine;
	private Address address;
	private int phone;
	private String email;
	private String ownerEmail;
	private String ownerPhone;
	private String status;
	private String website;
	private String userEmail;
	private String facebookPage;
	private String twitterPage;
	private String otherInfo;
	private Date lastUpdated;
	private Map<String, String> workingHours;
	private List<String> facility;
	private String authenticity;
	private String halalOfferings;
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;
	
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
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
	public String getHalalOfferings() {
		return halalOfferings;
	}
	public void setHalalOfferings(String halalOfferings) {
		this.halalOfferings = halalOfferings;
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
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
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
	public List<String> getCuisine() {
		return cuisine;
	}
	public void setCuisine(List<String> cuisine) {
		this.cuisine = cuisine;
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
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Map<String, String> getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(Map<String, String> workingHours) {
		this.workingHours = workingHours;
	}
	public List<String> getFacility() {
		return facility;
	}
	public void setFacility(List<String> facility) {
		this.facility = facility;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getAuthenticity() {
		return authenticity;
	}
	public void setAuthenticity(String authenticity) {
		this.authenticity = authenticity;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
