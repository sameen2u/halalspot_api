package com.halal.sa.data.entities;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	private String streetAddress;
	private String city;
	private int zipcode;
	private String state;
	private String country;
	private String landmark;
	private String locality;
	private Location location;
	private String fomattedAddress;
	
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFomattedAddress() {
		return fomattedAddress;
	}
	public void setFomattedAddress(String fomattedAddress) {
		this.fomattedAddress = fomattedAddress;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	public Address(){
		
	}
	public Address(String streetAddress, String city, int zipcode, String landmark) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.zipcode = zipcode;
		this.landmark = landmark;
	}
	

}
