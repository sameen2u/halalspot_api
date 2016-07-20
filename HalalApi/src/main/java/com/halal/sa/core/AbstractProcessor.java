package com.halal.sa.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.halal.sa.core.apiprocessor.ApiProcessor;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;
import com.halal.sa.core.exception.ErrorConstants;

public abstract class AbstractProcessor implements ApiProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractProcessor.class.getName());
	
	/**
	 * This method does the following:
	 * <ul>
	 * <li> Required parameters are not null </li>
	 * <li> Actual object passed is of the same class as the object</li>
	 * </ul>
	 * @param object - Object
	 * @param clas - Class<?>
	 * @throws ApiException
	 */
	protected final void validateParams(Object object, Class<?> clas) throws ApiException {
		
		if(object == null || clas == null || !clas.equals(object.getClass())) {
			
//			LOGGER.error(ApiLoggingConstants.API_INVALID_ARGUMENTS
//					+ "Expected input parameters are null / object is not an instance of the expected class");
			
			throw new ApiException(ErrorConstants.ERR_ENCOUNTERED_DURING_PROCESSING.toString());
		}
	}

	/**
	 * This method does the performas any calls to external services and processes the data.
	 * @param requestParameters - RequestParameters
	 * @throws ApiException 
	 */
	@Override
	public final AggregateData process(RequestParameters requestParameters) throws BadRequestException, ApiException {
		
		AggregateData aggregateData = retrieveData(requestParameters);
		
		if(aggregateData == null) {
			
//			LOGGER.error(ApiLoggingConstants.API_INVALID_ARGUMENTS + "aggregateData object is null");
			
			throw new ApiException(ErrorConstants.ERR_ENCOUNTERED_DURING_PROCESSING.toString());
			
		} else {
			
			return aggregateData;
		}
	}
}
