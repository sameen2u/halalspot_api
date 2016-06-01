package com.halal.sa.core;

import org.springframework.http.HttpHeaders;


public class ApiResponse<E> {
	
	private int statusCode;
	private E responseBody;
	private HttpHeaders httpHeaders;

	
	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

	public void setHttpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	public E getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(E responseDomain) {
		this.responseBody = responseDomain;
	}

	

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
