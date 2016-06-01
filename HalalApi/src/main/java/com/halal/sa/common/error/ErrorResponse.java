package com.halal.sa.common.error;

public class ErrorResponse extends AbstractErrorResponse{
	
	public ErrorResponse() {
		this(ErrorConstants.ERRORCODE_INTERNAL_ERROR, ErrorConstants.ERRORDESC_INTERNAL_ERROR);
//		LOGGER.error("ErrorResponse: Default constructor called");
	}
	
	public ErrorResponse(Integer id, String description) {
		super(id,description);
	}
}
