package com.halal.sa.process.searchbusiness;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.halal.sa.common.error.AbstractErrorResponse;
import com.halal.sa.common.error.ApiException;
import com.halal.sa.common.error.DefaultErrorProcessorImpl;
import com.halal.sa.common.error.DomainErrorConstants;
import com.halal.sa.common.error.ErrorCode;
import com.halal.sa.common.error.ErrorConstants;
import com.halal.sa.common.error.ErrorResponse;

@Component
public class SearchBusinessErrorProcessor extends DefaultErrorProcessorImpl{
	
	private Set<String> badRequestErrors;

	private Set<String> recordNotFoundErrors;
	
	private Set<String> internalServerErrors;
	
	private Set<String> serviceUnavailableErrors;
	
private void initialize() {
		
		badRequestErrors = new HashSet<String>();
		
		serviceUnavailableErrors = new HashSet<String>();
		
		internalServerErrors = new HashSet<String>();
		
		recordNotFoundErrors = new HashSet<String>();

		badRequestErrors.add(ErrorConstants.VAL_ERR_EMPTY_PARAMS_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_REPEATED_PARAMS_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_PARAMETER_NAMES_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_PARAMETER_COUNT_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_ACCEPTED_PARAM_VALUES_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_MIN_MAX_LENGTH_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_DATA_TYPE_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_EMPTY_PARAM_VALUES_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_IS_FILLED_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_IS_MANDATORY_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_IS_IN_RANGE_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_INVALID_OPERATION_CODE);
		badRequestErrors.add(ErrorConstants.VAL_ERR_AMBIGUOUS_OPERATION_CODE);
		badRequestErrors.add(DomainErrorConstants.ERR_INVALID_REQUEST_PARAMETERS);
		badRequestErrors.add(DomainErrorConstants. ERR_ROUTE_V1_BING_BAD_REQUEST);
		badRequestErrors.add(DomainErrorConstants.ERR_LOCATION_V1_BING_BAD_REQUEST);
		badRequestErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_BAD_REQUEST);
		badRequestErrors.add(ErrorCode.ERR_BAD_REQUEST.toString());
	
		
		serviceUnavailableErrors.add(DomainErrorConstants. ERR_ROUTE_V1_BING_SERVICE_UNAVAILABLE);
		serviceUnavailableErrors.add(DomainErrorConstants. ERR_ROUTE_V1_BING_HTTP_HOST_CONNECT);
		serviceUnavailableErrors.add(DomainErrorConstants. ERR_ROUTE_V1_BING_CONNECT_TIMEOUT);
		serviceUnavailableErrors.add(DomainErrorConstants. ERR_ROUTE_V1_BING_SOCKET_TIMEOUT);
		serviceUnavailableErrors.add(DomainErrorConstants. ERR_ROUTE_V1_BING_CLIENT_PROTOCOL);
		serviceUnavailableErrors.add(DomainErrorConstants.ERR_V1_BING_IO);
		serviceUnavailableErrors.add(DomainErrorConstants.ERR_LOCATION_V1_BING_DE_SERIALIZING_FROM_JSON);
		serviceUnavailableErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_SERVICE_UNAVAILABLE);
		serviceUnavailableErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_HTTP_HOST_CONNECT);
		serviceUnavailableErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_CONNECT_TIMEOUT);
		serviceUnavailableErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_SOCKET_TIMEOUT);
		serviceUnavailableErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_CLIENT_PROTOCOL);
		serviceUnavailableErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_IO);
		serviceUnavailableErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_DE_SERIALIZING_FROM_JSON);
		
		internalServerErrors.add(DomainErrorConstants.ERR_ROUTE_V1_BING_HTTP_RESPONSE_CODE);
		internalServerErrors.add(DomainErrorConstants.ERR_ROUTE_V1_BING_INTERNAL_SERVER);
		internalServerErrors.add(DomainErrorConstants.ERR_ROUTE_V1_BING_UNAUTHORIZED);
		internalServerErrors.add(DomainErrorConstants.ERR_ROUTE_V1_BING_CONNECTION_POOL_TIMEOUT);
		internalServerErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_HTTP_RESPONSE_CODE);
		internalServerErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_INTERNAL_SERVER);
		internalServerErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_UNAUTHORIZED);
		internalServerErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_CONNECTION_POOL_TIMEOUT);
		
		recordNotFoundErrors.add(DomainErrorConstants.ERR_ROUTE_V1_BING_RECORD_NOT_FOUND);
		recordNotFoundErrors.add(DomainErrorConstants.ERR_SPATIALQUERY_V1_BING_RECORD_NOT_FOUND);
		recordNotFoundErrors.add(DomainErrorConstants.ERR_V2_STORESEARCH_AMBIGUOUS_ADDRESS);
	}
	
	
	@Override
	public ErrorResponse buildErrorResponse(ApiException apiException) {

		ErrorResponse errorResponse = null;
		initialize();
		
		if (apiException != null) {
			errorResponse  = new ErrorResponse(ErrorConstants.ERRORCODE_INTERNAL_ERROR, apiException.getErrorMessage());
			populateIdAndType(errorResponse, apiException.getErrorCode());
			populateDescription(errorResponse, apiException.getErrorMessage());
		
		}
		return errorResponse;
	}
	
	/*
	 * Populates error id and type
	 */
	private void populateIdAndType(final ErrorResponse errorResponse,
			final String errorCode) {
	
		
		if (errorResponse != null){
			
			if (StringUtils.isNotBlank(errorCode)){

				if (badRequestErrors.contains(errorCode)){
					//bad request error status
					errorResponse.setId(400);
					
				} else if (recordNotFoundErrors.contains(errorCode)){
					//not found error stATUS
					errorResponse.setId(404);
					
				} else if (internalServerErrors.contains(errorCode)){
					//internal server error status
					errorResponse.setId(500);
					
				} else {
					//internal server error status
					errorResponse.setId(500);
				}
				
				errorResponse.setType(errorCode);
				
			} else {
				//internal server error status
				errorResponse.setId(500);
			}
		}
	}
	
	/*
	 * Populates error description
	 */
	private void populateDescription(final AbstractErrorResponse errorResponse,
			final String errorMessage) {
		
		if (errorResponse != null && StringUtils.isNotBlank(errorMessage)){
						
			errorResponse.setDescription(errorMessage);
		}
	}

}
