package com.halal.sa.processor.searchbusiness;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.halal.sa.core.ApiWorkflow;
import com.halal.sa.core.apiprocessor.ApiErrorProcessor;
import com.halal.sa.core.apiprocessor.ApiPostProcessor;
import com.halal.sa.core.apiprocessor.ApiPreProcessor;
import com.halal.sa.core.apiprocessor.ApiProcessor;

@Component
public class SearchBusinessApiWorkflow extends ApiWorkflow{
	
	@Override
	@Resource(name="searchBusinessPreProcessor")
	public void setApiPreprocessor(ApiPreProcessor apiPreprocessor) {
		super.setApiPreprocessor(apiPreprocessor);
	}
	
	@Override
	@Resource(name="searchBusinessProcessor")
	public void setApiProcessor(ApiProcessor apiProcessor) {
		super.setApiProcessor(apiProcessor);
	}
	
	@Override
	@Resource(name="searchBusinessPostProcessor")
	public void setApiPostprocessor(ApiPostProcessor apiPostprocessor) {
		super.setApiPostprocessor(apiPostprocessor);
	}
	
	@Override
	@Resource(name="searchBusinessErrorProcessor")
	public void setApiErrorProcessor(ApiErrorProcessor apiErrorProcessor) {
		super.setApiErrorProcessor(apiErrorProcessor);
	}

}
