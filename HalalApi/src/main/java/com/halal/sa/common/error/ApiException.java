package com.halal.sa.common.error;

public class ApiException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7861290553132780319L;

	private String errorCode;
	
	private String errorMessage;
	
	public ApiException(){
	}
	
	public ApiException(String errorCode){
		super(errorCode);
		this.errorCode = errorCode;
	}
	
	public ApiException(String errorCode, Throwable throwable) {
		super(errorCode, throwable);
		this.errorCode = errorCode;
	}
	
	public ApiException(String errorCode, String message) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorMessage = message;
	}
	
	public ApiException(String errorCode, String message, Throwable throwable) {
		super(errorCode, throwable);
		this.errorCode = errorCode;
		this.errorMessage = message;
	}
	
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

}
