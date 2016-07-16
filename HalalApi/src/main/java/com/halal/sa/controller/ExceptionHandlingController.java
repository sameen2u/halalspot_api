package com.halal.sa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.halal.sa.common.error.ErrorResponse;
import com.halal.sa.core.exception.BadRequestException;

@ControllerAdvice
public class ExceptionHandlingController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

	
	
	@ExceptionHandler(BadRequestException.class)
	public <T> ResponseEntity<T> handleBadRequestException(HttpServletRequest request,
			HttpServletResponse response, Exception e) {
		
		LOGGER.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
		return processErrorResponse(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler({ApplicationException.class, ServletException.class, JsonProcessingException.class, IOException.class})
//	public <T> ResponseEntity<T> handleApplicationException(HttpServletRequest request,
//			HttpServletResponse response, Exception e) {
//		
//		LOGGER.error(e.getMessage(), e);
//		ErrorResponse errorResponse = new ErrorResponse(500, e.getMessage());
//		return processErrorResponse(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@ExceptionHandler(Exception.class)
	public <T> ResponseEntity<T> handleError(HttpServletRequest request, HttpServletResponse response, Exception e) {

		
		LOGGER.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse(500, e.getMessage());
		return processErrorResponse(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private <T> ResponseEntity<T> processErrorResponse(ErrorResponse errorResponse, HttpStatus httpStatus){
		
		return new ResponseEntity<T>((T) errorResponse, httpStatus);
	}

}
