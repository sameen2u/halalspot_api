package com.halal.sa.core.exception;

public class ErrorResponse{
	
	String error;
	String description;
	
	
//	public ErrorResponse() {
//		this(ErrorConstants.ERRORCODE_INTERNAL_ERROR, ErrorConstants.ERRORDESC_INTERNAL_ERROR);
////		LOGGER.error("ErrorResponse: Default constructor called");
//	}
	
	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ErrorResponse(String error, String description) {
//		super(id,description);
		this.error = error;
		this.description = description;
	}
}
