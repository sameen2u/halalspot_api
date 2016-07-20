package com.halal.sa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;
import com.halal.sa.core.exception.ErrorResponse;

@ControllerAdvice
public class ExceptionHandlingController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

	
	
	@ExceptionHandler(BadRequestException.class)
	public <T> ResponseEntity<T> handleBadRequestException(HttpServletRequest request,
			HttpServletResponse response, BadRequestException e) {
		
		LOGGER.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST_IN_VALIDATION", e.getMessage());
		return processErrorResponse(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiException.class)
	public <T> ResponseEntity<T> handleApplicationException(HttpServletRequest request,
			HttpServletResponse response, ApiException e) {
		
		LOGGER.error(e.getMessage(), e);
		ErrorResponse errorResponse = new ErrorResponse("API_EXCEPTION_IN_APPLICATION", e.getMessage());
		return processErrorResponse(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public <T> ResponseEntity<T> handleError(HttpServletRequest request, HttpServletResponse response, Exception e) {

		
		LOGGER.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", e.getMessage());
		return processErrorResponse(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private <T> ResponseEntity<T> processErrorResponse(ErrorResponse errorResponse, HttpStatus httpStatus){
		
		return new ResponseEntity<T>((T) errorResponse, httpStatus);
	}

}
