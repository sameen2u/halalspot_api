package com.halal.sa.core;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.halal.sa.common.ServiceCommonConstants;
import com.halal.sa.common.error.ApiException;
import com.halal.sa.common.error.ErrorConstants;
import com.halal.sa.core.processor.ApiPostProcessor;

public abstract class AbstractPostProcessor implements ApiPostProcessor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractPostProcessor.class.getName());

	/**
	 * postProcess method is to generate the ApiResponse as per the expected
	 * output of the results
	 * 
	 * @param <T>
	 * 
	 * @param apiRequest
	 * @param requestParameters
	 * @param aggregateData
	 * 
	 * @return apiResponse
	 */
	@Override
	public <T> ApiResponse<T> postProcess(ApiRequest apiRequest,
			RequestParameters requestParameters, AggregateData aggregateData)
			throws ApiException {

		T serviceResource = transform(requestParameters, aggregateData);

		if (serviceResource == null) {
//			LOGGER.error(ApiLoggingConstants.API_INVALID_ARGUMENTS
//					+ "serviceResource object is null");
			throw new ApiException(
					ErrorConstants.ERR_ENCOUNTERED_DURING_PROCESSING.toString());
		}

		ApiResponse<T> apiResponse = buildApiResponse(serviceResource, aggregateData);

		return apiResponse;
	}

	/**
	 * buildApiResponse generates the ApiResponse object for the current
	 * apiRequest
	 * 
	 * @param apiRequest
	 *            - ApiRequest
	 * @param serviceResource
	 *            - SerializableEntity
	 * @return ApiResponse - AggregateData
	 */
	protected <T> ApiResponse<T> buildApiResponse(T serviceResource, AggregateData aggregateData) {

		ApiResponse<T> apiResponse = new ApiResponse<T>();
		apiResponse.setResponseBody(serviceResource);
		apiResponse.setStatusCode(ServiceCommonConstants.HTTP_STATUS_OK);
		HttpHeaders headers = new HttpHeaders();
		headers.put(ServiceCommonConstants.CONTENT_TYPE,
				Arrays.asList(ServiceCommonConstants.CONTENT_TYPE_JSON));
		apiResponse.setHttpHeaders(headers);
		
		return apiResponse;
	}

}
