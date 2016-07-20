package com.halal.sa.core.exception;


public class BadRequestException extends Exception{
	
	private String errorCode;
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
	     super();
	 }

	 public BadRequestException(String message) {
	     super(message);
	 }

	 public BadRequestException(String message, Throwable cause) {
	     super(message, cause);
	 }
	 
	 public BadRequestException(String errorCode, String message) {
	     super(message);
	     this.errorCode = errorCode;
	 }

	 public BadRequestException(Throwable cause) {
	     super(cause);
	 }

	public String getErrorCode() {
		return errorCode;
	}
	 
}
