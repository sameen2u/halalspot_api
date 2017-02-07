package com.halal.sa.controller.vo;

/*
 * this class is specially designed for apps
 */
public class BizCategoryVO {
	
	private String category;
	private String name;
	private int count;
	private double distance;
	private String distanceUnit;
	private String imageUrl;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getDistanceUnit() {
		return distanceUnit;
	}
	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
