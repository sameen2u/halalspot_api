package com.halal.sa.data.entities;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address implements Serializable{
	
	@NotEmpty
	private String streetAddress;
	private String city;
	private int pincode;
	private String landmark;
	private String locality;
	private Location location;

	
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
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	public Address(){
		
	}
	public Address(String streetAddress, String city, int pincode, String landmark) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.pincode = pincode;
		this.landmark = landmark;
	}
	

}
