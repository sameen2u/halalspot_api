package com.halal.sa.common;

public enum CommonEnum {
	
	KEYWORD("keyword");
	
	private String name;
	
	private CommonEnum(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

}
