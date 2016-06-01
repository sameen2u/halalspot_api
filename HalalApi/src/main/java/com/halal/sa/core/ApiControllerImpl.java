package com.halal.sa.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.common.error.ErrorConstants;
import com.halal.sa.common.error.ErrorResponse;
import com.halal.sa.controller.ApiController;

@Component
public class ApiControllerImpl implements ApiController {

	@Autowired
	ApiResponseGenerator apiResponseGenerator;
	
	@Override
	public final <T> ResponseEntity<T> execute(final ApiRequest apiRequest,
			final ApiWorkflow apiWorkflow) {

		ResponseEntity<T> response = null;
		ApiResponse<T> apiResponse;
		
		try {
			if (apiRequest == null || apiWorkflow == null
					|| apiWorkflow.getApiPreprocessor() == null
					|| apiWorkflow.getApiProcessor() == null
					|| apiWorkflow.getApiPostprocessor() == null
					|| apiWorkflow.getApiErrorProcessor() == null) {

//				LOGGER.error(ApiLoggingConstants.API_INVALID_ARGUMENTS
//						, "Expected input parameters are null");
				throw new ApiException(
						ErrorConstants.ERR_ENCOUNTERED_DURING_PROCESSING.toString());
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
					response = (ResponseEntity<T>) getErrorResponse();
				}
			}

		} catch (Exception e) {

//			LOGGER.error(e.getMessage(), e);
//			LOGGER.error(ApiLoggingConstants.API_RESPONSE_GENERATION_FAILED
//					+ e.getMessage());
			response = (ResponseEntity<T>) getErrorResponse();
		}

		return response;
	}
	
	private ResponseEntity<ErrorResponse> getErrorResponse(){
		
		ErrorResponse internalServerErrorResponse = new ErrorResponse(
				ErrorConstants.ERRORCODE_INTERNAL_ERROR,
				ErrorConstants.ERRORDESC_INTERNAL_ERROR);
		 
		 ResponseEntity<ErrorResponse> errorResponse= new ResponseEntity<>(internalServerErrorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		return errorResponse;
		
	}

}
