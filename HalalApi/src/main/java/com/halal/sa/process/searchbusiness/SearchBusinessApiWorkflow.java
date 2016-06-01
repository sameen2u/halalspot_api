package com.halal.sa.process.searchbusiness;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.halal.sa.core.ApiWorkflow;
import com.halal.sa.core.processor.ApiErrorProcessor;
import com.halal.sa.core.processor.ApiPostProcessor;
import com.halal.sa.core.processor.ApiPreProcessor;
import com.halal.sa.core.processor.ApiProcessor;

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
