package com.halal.sa.core;

import org.springframework.cloud.util.UriInfo;
import org.springframework.http.HttpHeaders;


public class ApiRequest {

	private HttpHeaders httpHeaders;
	private UriInfo uriInfo;

public ApiRequest() {
		
	}
	
	public ApiRequest(final UriInfo uriInfo, final HttpHeaders httpHeaders,  final String payload) {
		
		this.httpHeaders = httpHeaders;
		this.uriInfo = uriInfo;
	}
	
	
	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}
	public void setHttpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}
	public UriInfo getUriInfo() {
		return uriInfo;
	}
	public void setUriInfo(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
		
}
