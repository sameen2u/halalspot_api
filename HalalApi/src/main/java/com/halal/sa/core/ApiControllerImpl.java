package com.halal.sa.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.halal.sa.controller.ApiController;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;
import com.halal.sa.core.exception.ErrorConstants;

@Component
public class ApiControllerImpl implements ApiController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiControllerImpl.class);


	@Autowired
	ApiResponseGenerator apiResponseGenerator;
	
	@Override
	public final <T> ResponseEntity<T> execute(final ApiRequest apiRequest,
			final ApiWorkflow apiWorkflow) throws ApiException, BadRequestException {

		ResponseEntity<T> response = null;
		ApiResponse<T> apiResponse;
		
//		try {
			if (apiRequest == null || apiWorkflow == null
					|| apiWorkflow.getApiPreprocessor() == null
					|| apiWorkflow.getApiProcessor() == null
					|| apiWorkflow.getApiPostprocessor() == null) {

				LOGGER.error("Invalid Argument"
						, "Expected input parameters are null");
				throw new ApiException(ErrorConstants.ERR_ENCOUNTERED_DURING_PROCESSING.toString());
			} else {
				
				RequestParameters requestParameters = apiWorkflow
						.getApiPreprocessor().preProcess(apiRequest);
				
				AggregateData aggregateData = apiWorkflow.getApiProcessor()
						.process(requestParameters);
				
				apiResponse = apiWorkflow.getApiPostprocessor()
						.postProcess(apiRequest, requestParameters,
								aggregateData);
				response = apiResponseGenerator.generateResponse(apiRequest, apiResponse);

				if (response == null) {
					throw new ApiException("Response is null");
				}
			}

//		} 
//		catch (Exception e) {
//
//			LOGGER.error(e.getMessage(), e);
//			LOGGER.error(ApiLoggingConstants.API_RESPONSE_GENERATION_FAILED
//					+ e.getMessage());
//			response = (ResponseEntity<T>) getErrorResponse(e.getMessage());
//		}
//
		return response;
//	}
	
//	private ResponseEntity<ErrorResponse> getErrorResponse(String msg){
//		String errorMsg = (msg != null)? msg : ErrorConstants.ERRORDESC_INTERNAL_ERROR;
//		
//		ErrorResponse internalServerErrorResponse = new ErrorResponse(
//				ErrorConstants.ERRORCODE_INTERNAL_ERROR,
//				errorMsg);
//		 
//		 ResponseEntity<ErrorResponse> errorResponse= new ResponseEntity<>(internalServerErrorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
//		return errorResponse;
//		
	}
			
}
