package com.halal.sa.core;

import java.util.List;

import org.springframework.cloud.util.UriInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;


public class ApiRequest {
	
	private MultiValueMap<String, String> headers;
	private MultiValueMap<String, String> requestParameters;
	private String payload;
	private Object request;

	public ApiRequest() {
		// Added a default constructor
	}

	public MultiValueMap<String, String> getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(
			MultiValueMap<String, String> requestParameters) {
		this.requestParameters = requestParameters;
	}

	public ApiRequest(final MultiValueMap<String, String> requestParameters,
			final MultiValueMap<String, String> headers) {
		this.requestParameters = requestParameters;
		this.headers = headers;
		this.payload = payload;
	}

	public MultiValueMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(MultiValueMap<String, String> headers) {
		this.headers = headers;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	/**
	 * Method returns the media type requested by the client, as determined by
	 * inspecting the request data in this order until a hit is found:
	 * 
	 * 1. Media type as found in the uriInfo query parameters. 2. Media type as
	 * found in the httpHeaders content type (only looks for JSON ). 3.
	 * Default the media type to JSON.
	 * 
	 * @return String - 
	 
	public String getMediaType() {

		String mediaType = getQueryParam(ServiceCommonConstants.URL_PARAM_RESPONSE_TYPE);

		if (mediaType == null) {
			mediaType = getMediaTypeFromHeaders();
		}

		if (mediaType == null) {
			mediaType = ServiceCommonConstants.MEDIA_TYPE_JSON;
		}

		return mediaType;
	}

	/**
	 * This method returns a String containing the paramKey's value from the
	 * uriInfo.
	 * 
	 * @param paramKey
	 *            - String
	 * @return String
	 */
	private String getQueryParam(String paramKey) {

		if (requestParameters != null) {

			List<String> paramList = requestParameters.get(paramKey);

			if (paramList != null && !paramList.isEmpty()) {
				return paramList.get(0);
			}
		}

		return null;
	}

	/**
	 * Returns the media type found in the content type in the headers.
	 * 
	 * @return String
	
	public String getMediaTypeFromHeaders() {

		String mediaType = null;
		String contentType = getContentType();

		if (contentType != null && contentType.toUpperCase().contains(
				ServiceCommonConstants.MEDIA_TYPE_JSON.toUpperCase())) {

				mediaType = ServiceCommonConstants.MEDIA_TYPE_JSON;
		}

		return mediaType;
	}
 */
	/**
	 * Returns the content-type value found in the headers.
	 * 
	 * @param headers2
	 *            - HttpHeaders
	 * @return String
	 
	private String getContentType() {
		if (headers != null) {
			List<String> contentTypes = headers
					.get(ServiceCommonConstants.CONTENT_TYPE);

			if (contentTypes != null && !contentTypes.isEmpty()) {
				return contentTypes.get(0);
			}
		}
		return null;
	}
	*/
}
