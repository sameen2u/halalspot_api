package com.halal.sa.core.exception;

public class ApiException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7861290553132780319L;

	private String errorCode;
	
//	private String errorMessage;
	
	public ApiException(){
	}
	
	public ApiException(String message){
		super(message);
	}
	
	public ApiException(String errorCode, Throwable throwable) {
		super(errorCode, throwable);
		this.errorCode = errorCode;
	}
	
	public ApiException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ApiException(String errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
	
	
	public String getErrorCode() {
		return errorCode;
	}

}
