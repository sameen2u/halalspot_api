package com.halal.sa.common.error;

public class ErrorResponseGenerator {
	
	private static final ErrorResponseGenerator INSTANCE = new ErrorResponseGenerator();	
	
	public static ErrorResponse INCOMPLETE_DATA_RESPONSE;
	
	static{
		INCOMPLETE_DATA_RESPONSE = new ErrorResponse(404,ErrorConstants.ERRORDESC_INCOMPLETE_DATA);
	}
//	private static final 
}
