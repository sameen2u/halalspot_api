package com.halal.sa.controller.vo;

/*
 * this class is specially designed for apps
 */
public class BizCategoryVO {
	
	private String id;
	private String name;
	private int count;
	private int radius;
	private String radiusUnit;
	private String imageUrl;
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public String getRadiusUnit() {
		return radiusUnit;
	}
	public void setRadiusUnit(String radiusUnit) {
		this.radiusUnit = radiusUnit;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
