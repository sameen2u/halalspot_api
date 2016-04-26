package com.halal.sa.data.entities;

import java.io.Serializable;

public class Address implements Serializable{
	
	private String streetAddress;
	private String streetAddress2;
	private String city;
	private int pincode;
	private String landmark;
	private Location location;

	
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
	public String getStreetAddress2() {
		return streetAddress2;
	}
	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
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
	public Address(String streetAddress, String streetAddress2,
			String city, int pincode, String landmark) {
		super();
		this.streetAddress = streetAddress;
		this.streetAddress2 = streetAddress2;
		this.city = city;
		this.pincode = pincode;
		this.landmark = landmark;
	}
	

}
