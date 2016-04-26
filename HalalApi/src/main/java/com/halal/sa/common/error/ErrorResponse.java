package com.halal.sa.common.error;

public class ErrorResponse {
	
	private String id;
	
	private String description;
	
	public ErrorResponse(){
		this(ErrorConstants.ERRORCODE_INTERNAL_ERROR, ErrorConstants.ERRORDESC_INTERNAL_ERROR);
	}

	public ErrorResponse(String id, String description){
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
