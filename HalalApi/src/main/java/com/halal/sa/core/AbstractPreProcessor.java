package com.halal.sa.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;

import com.halal.sa.core.apiprocessor.ApiPreProcessor;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.BadRequestException;
import com.halal.sa.core.exception.ErrorConstants;

/**
 * Class implements the template method for the preprocess step.Class also contains
 * validation methods that can be utilized by sub-classes.
 * @author HXR8301
 */
public abstract class AbstractPreProcessor implements ApiPreProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPreProcessor.class.getName());


	@Override
	public RequestParameters preProcess(ApiRequest apiRequest)
			throws ApiException, BadRequestException {
		RequestParameters requestParameters = validate(apiRequest);
		if(requestParameters == null) {
//			LOGGER.error(ApiLoggingConstants.API_INVALID_ARGUMENTS , "requestParameters object is null");
			throw new ApiException(ErrorConstants.ERR_ENCOUNTERED_DURING_PROCESSING.toString());
		}
//		setDefaultValues(requestParameters);

		return requestParameters;
	}
	
	
	
	/**
	 * This convenience method parses the comma separated request query or
	 * path parameters into collection.
	 * <br/>
	 * Condition: Repeated request parameters should be checked using a utility method prior.
	 * <br/>
	 * 
	 * @param requestParameters
	 * @return final Map<String, Set<String>>
	 */
	/*protected final Map<String, Set<String>> splitParameters(final MultiValueMap<String, String> requestParameters,
			final List<RequestParamDefinition> requestParamDefinitions) {
		
		Map<String, Set<String>> parameterValuesByName = Collections.emptyMap();
		
		if (requestParameters != null && !requestParameters.isEmpty()){
			
			parameterValuesByName = new HashMap<String, Set<String>>();
			List<String> parameterValuesList;
			String parameterValue;
			
			for (String parameterName : requestParameters.keySet()) {
				if (StringUtils.isNotBlank(requestParameters.getFirst(parameterName))) {
					parameterValue = requestParameters.getFirst(parameterName);
					
					// calling the new method added as part of sonar fix,
					// this method adds the paramValue to the parameterValuesByName
					addParamValueToList(requestParamDefinitions,
							parameterValuesByName, parameterValue,
							parameterName);
				}
			}
		}
		
		return parameterValuesByName;
	}

	*//** This Method added to reduce the cyclometrix code complexity
	 * @param requestParamDefinitions
	 * @param parameterValuesByName
	 * @param parameterValue
	 * @param parameterName
	 *//*
	private void addParamValueToList(
			final List<RequestParamDefinition> requestParamDefinitions,
			Map<String, Set<String>> parameterValuesByName,
			String parameterValue, String parameterName) {
		List<String> parameterValuesList;
		if (isKeywordParameter(requestParamDefinitions, parameterName)){
				parameterValuesList = new ArrayList<String>();
				parameterValuesList.add(parameterValue);
				parameterValuesByName.put(parameterName, new LinkedHashSet<String>(parameterValuesList));
		} else {
			parameterValuesList = new ArrayList<String>(Arrays.asList(parameterValue.split("\\s*,\\s*")));
			if (!parameterValuesList.isEmpty()) {
				parameterValuesByName.put(parameterName, new LinkedHashSet<String>(parameterValuesList));
			}
		}
	}
	
	*//**
	 * This method determines if a keyword request parameter was included in the request.
	 * @param requestParamDefinitions - final List<RequestParamDefinition>
	 * @param keyword - final String
	 * @return final boolean
	 *//*
	private final boolean isKeywordParameter(final List<RequestParamDefinition> requestParamDefinitions, final String keyword) {
		
		boolean keywordParameter = false;
		if (requestParamDefinitions != null && !requestParamDefinitions.isEmpty() && StringUtils.isNotBlank(keyword)) {
			for (RequestParamDefinition requestParamDefinition: requestParamDefinitions) {
				if (StringUtils.equalsIgnoreCase(keyword, requestParamDefinition.getName())){
					keywordParameter = requestParamDefinition.isKeyword();
				}
			}
		}
		
		return keywordParameter;
	}
	
	*//**
	 * This method does the following basic validation:
	 * <ul>
	 * <li>UriInfo and HttpHeaders received</li>
	 * <li>Empty params</li>
	 * <li>Repeated params</li>
	 * <li>Mandatory - Parameters should be present in the url</li>
	 * <li>Count - Ensures maximum number of parameter values</li>
	 * <li>Filled - Value for the parameter is present or not</li>
	 * <li>Data type - Data type correctness</li>
	 * <li>Min and Max Length - If the length of the parameter value is within
	 * the specified limit</li>
	 * </li>Accepted Parameter Values - Validates the param value against a set
	 * of allowed param values</li>
	 * </ul>
	 * 
	 * @param apiRequest - ApiRequest
	 * @param requestParamDefinitions - final List<RequestParamDefinition>
	 * @throws ApiException
	 *//*
	protected Map<String, Set<String>> baseValidateAndGetParameters(ApiRequest apiRequest,
			final List<RequestParamDefinition> requestParamDefinitions)	throws ApiException {
		
		ValidationUtil.getInstance().validateRequest(apiRequest);
		ValidationUtil.getInstance().validateRequestParamDefinition(requestParamDefinitions);
		return baseValidateAndGetParameters(apiRequest.getRequestParameters(), requestParamDefinitions);
	}
	
	*//**
	 * This method does the following basic validation: <br/>
	 * 1) Mandatory - Parameters should be present in the url <br/> 
	 * 2) Count - Ensures maximum number of parameter values <br/>
	 * 3) Filled - Value for the parameter is present or not <br/>
	 * 4) Data type - Data type correctness <br/>
	 * 5) Min and Max Length - If the length of the parameter value is within the specified limit <br/>
	 * 6) Accepted Parameter Values - Validates the param value against a set of allowed param values
	 * 
	 * @param requestParameters - final MultivaluedMap<String, String>
	 * @param requestParamDefinitions - final List<RequestParamDefinition>
	 * @throws ApiException
	 *//*
	protected Map<String, Set<String>> baseValidateAndGetParameters(final MultiValueMap<String, String> requestParameters,
			final List<RequestParamDefinition> requestParamDefinitions) throws ApiException {		
		
		ValidationUtil.getInstance().validateEmptyParams(requestParameters);		
		ValidationUtil.getInstance().validateRepeatedParamNames(requestParameters);
		
		return parseRequestParams(requestParameters,requestParamDefinitions);
	}

	*//**
	 * This method parses request parameters.
	 * @param requestParameters - final MultivaluedMap<String, String>
	 * @param requestParamDefinitions - final List<RequestParamDefinition>
	 * @return Map<String, Set<String>>
	 * @throws ApiException
	 *//*
	protected Map<String, Set<String>> parseRequestParams(final MultiValueMap<String, String> requestParameters,
			final List<RequestParamDefinition> requestParamDefinitions)	throws ApiException {
		
		Map<String, Set<String>> parsedRequestParameters = Collections.emptyMap();
		
		if(requestParamDefinitions != null && !requestParamDefinitions.isEmpty()) {
			
			parsedRequestParameters = splitParameters(requestParameters, requestParamDefinitions);
			Set<String> parsedRequestParameterValues;
			
			for(RequestParamDefinition requestParamDefinition : requestParamDefinitions) {
				
				ValidationUtil.getInstance().validateIsMandatory(parsedRequestParameters, requestParamDefinition);
				
				if(parsedRequestParameters.containsKey(requestParamDefinition.getName()) 
						&& !requestParamDefinition.getName().equals(ServiceCommonConstants.URL_PARAM_RESPONSE_TYPE)) {
					
					parsedRequestParameterValues = parsedRequestParameters.get(	requestParamDefinition.getName());					
					ValidationUtil.getInstance().validateEmptyParamValues(parsedRequestParameterValues, requestParamDefinition);					
					ValidationUtil.getInstance().validateParameterValueCount(parsedRequestParameterValues, requestParamDefinition);
					
					for (String parsedRequestParamValue : parsedRequestParameterValues) {
						
						// calling the new method which is added as part of sonar fix
						parsedRequestParamValue = validateParsedRequestParams(
								requestParamDefinition, parsedRequestParamValue);
					}
				}
			}
			setupDefaultValues(parsedRequestParameters, requestParamDefinitions);
		}
		
		return parsedRequestParameters;
	}

	*//**This method is added as part of sonar fix, This validates the parsedRequestParameterValues
	 * @param requestParamDefinition
	 * @param parsedRequestParamValue
	 * @return
	 * @throws ApiException
	 *//*
	private String validateParsedRequestParams(
			RequestParamDefinition requestParamDefinition,
			String parsedRequestParamValue) throws ApiException {
		ValidationUtil.getInstance().validateIsFilled(parsedRequestParamValue, requestParamDefinition);						
		parsedRequestParamValue = StringUtils.trim(parsedRequestParamValue);						
		ValidationUtil.getInstance().validateDataType(parsedRequestParamValue, requestParamDefinition);			
		ValidationUtil.getInstance().validateAcceptedParamValues(parsedRequestParamValue, requestParamDefinition);
		ValidationUtil.getInstance().validateMinAndMaxLength(parsedRequestParamValue, requestParamDefinition);													
		ValidationUtil.getInstance().validateAcceptedNumericRange(parsedRequestParamValue, requestParamDefinition);
		return parsedRequestParamValue;
	}
	
	*//**
	 * This method set the default values from the requestParameterDefinition
	 * 
	 * @param requestParameters - Map<String, Set<String>>
	 * @param requestParamDefinitions - final List<RequestParamDefinition>
	 *//*
	private final void setupDefaultValues(Map<String, Set<String>> requestParameters, 
			final List<RequestParamDefinition> requestParamDefinitions) {
		
		if (requestParamDefinitions != null && !requestParamDefinitions.isEmpty()){
			
			if (requestParameters == null || requestParameters.isEmpty()){
				
				requestParameters = new HashMap<String, Set<String>>();
			}

			Set<String> requestParameterValues;
			
			for (RequestParamDefinition requestParamDefinition: requestParamDefinitions) {
				
				if (!requestParameters.containsKey(requestParamDefinition.getName()) &&
						StringUtils.isNotBlank(requestParamDefinition.getDefaultStringValue())) {
					
					requestParameterValues = new HashSet<String>();
					requestParameterValues.add(requestParamDefinition.getDefaultStringValue());
					requestParameters.put(requestParamDefinition.getName(), requestParameterValues);
				}
			}
		}
	}
	
	*//**
	 * This convenience method retrieves the requested string parameter.
	 * 
	 * @param requestParameters - final Map<String, Set<String>>
	 * @param requestParameterName - final String
	 * @return final String
	 *//*
	protected final String getStringRequestParameter(final Map<String, Set<String>> requestParameters,
			final String requestParameterName) {
		
		String reqParam = null;
		
		if (requestParameters != null && !requestParameters.isEmpty() && StringUtils.isNotBlank(requestParameterName)) {
			if (requestParameters.containsKey(requestParameterName)) {
				
				// Caling new method added as part of sonar fix
				reqParam = trimReqParamVal(requestParameters,
						requestParameterName, reqParam);
			}
		}
	
		return reqParam;
	}

	*//**This method is added as part of sonar fix
	 * @param requestParameters
	 * @param requestParameterName
	 * @param reqParam
	 * @return
	 *//*
	private String trimReqParamVal(
			final Map<String, Set<String>> requestParameters,
			final String requestParameterName, String reqParam) {
		Set<String> reqParamValues = requestParameters.get(requestParameterName);
		if (reqParamValues!=null && !reqParamValues.isEmpty()) {
			for (String reqParamValue : reqParamValues) {
				
				Adding the trim for inputs like storeid=+00121. When the request comes to the 
				 container, the + doesnt come and parse method returns " 00121". This is not getting
				 recognized as a valid integer due to space. 
				reqParam= StringUtils.trim(reqParamValue);
			}
		}
		return reqParam;
	}
	
	*//**
	 * This convenience method retrieves the requested double parameter.
	 * 
	 * @param requestParameters - final Map<String, Set<String>>
	 * @param requestParameterName - final String
	 * @return final Double
	 *//*
	protected final Double getDoubleRequestParameter(final Map<String, Set<String>> requestParameters, 
			final String requestParameterName) {
		
		Double reqParam = null;
		String reqParamString = getStringRequestParameter(requestParameters, requestParameterName);
		
		if(StringUtils.isNotBlank(reqParamString)) {
			
			try {
				reqParam = Double.parseDouble(reqParamString);
				
			} catch(NumberFormatException nfe) {
				
				reqParam = null;
			}
		}
	
		return reqParam;
	}
	
	*//**
	 * This convenience method retrieves the requested Integer parameter.
	 * 
	 * @param requestParameters - final Map<String, Set<String>>
	 * @param requestParameterName - final String
	 * @return final Integer
	 *//*
	protected final Integer getIntegerRequestParameter(final Map<String, Set<String>> requestParameters, 
			final String requestParameterName) {
		
		Integer reqParam = null;
		String reqParamString = getStringRequestParameter(requestParameters, requestParameterName);
		
		if (StringUtils.isNumeric(reqParamString)) {
			
			reqParam = Integer.valueOf(reqParamString);
		}
		
		
		return reqParam;
	}
	
	*//**
	 * This convenience method retrieves the requested boolean parameter.
	 * 
	 * @param requestParameters - final Map<String, Set<String>>
	 * @param requestParameterName - final String
	 * @return final Boolean
	 *//*
	protected final Boolean getBooleanRequestParameter(final Map<String, Set<String>> requestParameters, 
			final String requestParameterName) {
		
		Boolean reqParam = null;
		String reqParamString = getStringRequestParameter(requestParameters, requestParameterName);
		
		if (StringUtils.equalsIgnoreCase(reqParamString, "true")) {
		
			reqParam = Boolean.TRUE;
			
		} else if (StringUtils.equalsIgnoreCase(reqParamString, "false")) {
			
			reqParam = Boolean.FALSE;
		}
		
		
		return reqParam;
	}
	
	*//**
	 * This convenience method retrieves the requested primitive boolean parameter.
	 * 
	 * @param requestParameters - final Map<String, Set<String>>
	 * @param requestParameterName - final String
	 * @return final boolean
	 *//*
	protected final boolean getPrimitiveBooleanRequestParameter(final Map<String, Set<String>> requestParameters, 
			final String requestParameterName) {
		

		boolean reqParam = false;
		String reqParamString = getStringRequestParameter(requestParameters, requestParameterName);
		
		if (StringUtils.equalsIgnoreCase(reqParamString, "true")) {
		
			reqParam = true;
		}
		
		
		return reqParam;
	}

	*//**
	 * This method checks whether all mandatory fields are present.
	 * 
	 * @param requestParameters - final MultivaluedMap<String, String>
	 * @param requestParamDefinitions - final List<RequestParamDefinition>
	 * @return final boolean
	 * @throws ApiException
	 *//*
	protected final boolean isAllMandatoryFieldsPresent(final MultiValueMap<String, String> requestParameters, 
			final List<RequestParamDefinition> requestParamDefinitions) throws ApiException {
		
		boolean allMandatoryFieldsPresent = true;
		ValidationUtil.getInstance().validateEmptyParams(requestParameters);
		
		if (requestParamDefinitions != null && !requestParamDefinitions.isEmpty()){
			
			for (RequestParamDefinition requestParamDefinition: requestParamDefinitions){
				
				if (requestParamDefinition.isMandatory() &&	(!requestParameters.containsKey(requestParamDefinition.getName()) ||
						StringUtils.isBlank(requestParameters.getFirst(requestParamDefinition.getName())))){
					
					allMandatoryFieldsPresent = false;
				}
			}
		}
		
		return allMandatoryFieldsPresent;
	}
	
	*//**
	 * This method validate below discrepancies in operation: <br/>
	 * 1. Multiple Ambiguous operations. <br/>
	 * 2. No operation. <br/>
	 * 
	 * And returns a single operation.<br/>
	 * 
	 * @param operations - final Set<T>
	 * @return final <T> T
	 *//*
	protected final <T> T validateAndGetOperation(final Set<T> operations) throws ApiException {
		
		T t = null;
		
		if (operations == null || operations.isEmpty()) {
			
			throw new ApiException(ErrorCode.ERR_BAD_REQUEST.toString(), ErrorConstants.VAL_ERR_INVALID_OPERATION_MESSAGE);
		}
		
		if (operations != null && operations.size() > 1) {
			
			throw new ApiException(ErrorCode.ERR_BAD_REQUEST.toString(), ErrorConstants.VAL_ERR_AMBIGUOUS_OPERATION_MESSAGE);
		}
		
		for (T typeT : operations) {
			t = typeT;
		}
		
		return t;
	}	
	
	*//**
	 * Template method for preProcess step.
	 * @param apiRequest - The request object containing uriInfo and httpHeaders
	 * @throws ApiException
	 * @return final RequestParameters
	 *//*
	public final RequestParameters preProcess(ApiRequest apiRequest) throws ApiException {
		
		RequestParameters requestParameters = validate(apiRequest);
		if(requestParameters == null) {
			LOGGER.error(ApiLoggingConstants.API_INVALID_ARGUMENTS , "requestParameters object is null");
			throw new ApiException(ErrorCode.ERR_ENCOUNTERED_DURING_PROCESSING.toString());
		}
		setDefaultValues(requestParameters);

		return requestParameters;
	}
	
	*//**
	 * Converts the Set of String to Set of Integer 
	 * 
	 * @param setOfString - final Set<String>
	 * @return Set<Integer>
	 *//*
	protected final Set<Integer> getIntegerSetFromStringSet(final Set<String> setOfString) {
		
		Set<Integer> setOfInteger = new HashSet<Integer>();
		
		for(String str: setOfString) {
			
			Adding the trim for inputs like storeid=+00121. When the request comes to the 
			 container, the + doesnt come and parse method returns " 00121". This is not getting
			 recognized as a valid integer due to space.
			setOfInteger.add(Integer.parseInt(StringUtils.trim(str)));
		}
		
		return setOfInteger;
	}
*/
}
