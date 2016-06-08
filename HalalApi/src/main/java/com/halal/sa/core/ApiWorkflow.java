package com.halal.sa.core;

import org.springframework.beans.factory.annotation.Required;

import com.halal.sa.core.apiprocessor.ApiErrorProcessor;
import com.halal.sa.core.apiprocessor.ApiPostProcessor;
import com.halal.sa.core.apiprocessor.ApiPreProcessor;
import com.halal.sa.core.apiprocessor.ApiProcessor;

public class ApiWorkflow {

	private ApiPreProcessor apiPreprocessor;
	private ApiProcessor apiProcessor;
	private ApiPostProcessor apiPostprocessor;
	private ApiErrorProcessor apiErrorProcessor;

	public ApiPreProcessor getApiPreprocessor() {
		return apiPreprocessor;
	}

	@Required
	public void setApiPreprocessor(ApiPreProcessor apiPreprocessor) {
		this.apiPreprocessor = apiPreprocessor;
	}

	public ApiProcessor getApiProcessor() {
		return apiProcessor;
	}

	@Required
	public void setApiProcessor(ApiProcessor apiProcessor) {
		this.apiProcessor = apiProcessor;
	}

	public ApiPostProcessor getApiPostprocessor() {
		return apiPostprocessor;
	}

	@Required
	public void setApiPostprocessor(ApiPostProcessor apiPostprocessor) {
		this.apiPostprocessor = apiPostprocessor;
	}

	public ApiErrorProcessor getApiErrorProcessor() {
		return apiErrorProcessor;
	}

	@Required
	public void setApiErrorProcessor(ApiErrorProcessor apiErrorProcessor) {
		this.apiErrorProcessor = apiErrorProcessor;
	}
	
}
