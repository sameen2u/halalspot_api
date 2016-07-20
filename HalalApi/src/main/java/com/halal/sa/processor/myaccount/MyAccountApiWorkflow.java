package com.halal.sa.processor.myaccount;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.halal.sa.core.ApiWorkflow;
import com.halal.sa.core.apiprocessor.ApiErrorProcessor;
import com.halal.sa.core.apiprocessor.ApiPostProcessor;
import com.halal.sa.core.apiprocessor.ApiPreProcessor;
import com.halal.sa.core.apiprocessor.ApiProcessor;

@Component
public class MyAccountApiWorkflow extends ApiWorkflow{
	
	@Override
	@Resource(name="myAccountPreProcessor")
	public void setApiPreprocessor(ApiPreProcessor apiPreprocessor) {
		super.setApiPreprocessor(apiPreprocessor);
	}
	
	@Override
	@Resource(name="myAccountProcessor")
	public void setApiProcessor(ApiProcessor apiProcessor) {
		super.setApiProcessor(apiProcessor);
	}
	
	@Override
	@Resource(name="myAccountPostProcessor")
	public void setApiPostprocessor(ApiPostProcessor apiPostprocessor) {
		super.setApiPostprocessor(apiPostprocessor);
	}
	
}
