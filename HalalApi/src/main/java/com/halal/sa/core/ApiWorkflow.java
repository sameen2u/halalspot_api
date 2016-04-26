package com.halal.sa.core;

import com.halal.sa.core.processor.ApiErrorProcessor;
import com.halal.sa.core.processor.ApiPostProcessor;
import com.halal.sa.core.processor.ApiPreProcessor;
import com.halal.sa.core.processor.ApiProcessor;

public class ApiWorkflow {

	private ApiPreProcessor apiPreProcessor;
	private ApiProcessor apiProcessor;
	private ApiPostProcessor apiPostProcessor;	
	private ApiErrorProcessor apiErrorProcessor;
	
	public ApiPreProcessor getApiPreProcessor() {
		return apiPreProcessor;
	}
	public void setApiPreProcessor(ApiPreProcessor apiPreProcessor) {
		this.apiPreProcessor = apiPreProcessor;
	}
	public ApiProcessor getApiProcessor() {
		return apiProcessor;
	}
	public void setApiProcessor(ApiProcessor apiProcessor) {
		this.apiProcessor = apiProcessor;
	}
	public ApiPostProcessor getApiPostProcessor() {
		return apiPostProcessor;
	}
	public void setApiPostProcessor(ApiPostProcessor apiPostProcessor) {
		this.apiPostProcessor = apiPostProcessor;
	}
	public ApiErrorProcessor getApiErrorProcessor() {
		return apiErrorProcessor;
	}
	public void setApiErrorProcessor(ApiErrorProcessor apiErrorProcessor) {
		this.apiErrorProcessor = apiErrorProcessor;
	}
	
}
