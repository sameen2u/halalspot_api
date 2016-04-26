package com.halal.sa.core;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.controller.ApiController;
import com.halal.sa.core.response.AggregateData;

public class ApiControllerImpl implements ApiController {

	@Override
	public void execute(ApiRequest apiRequest, ApiWorkflow apiWorkflow) {
		// TODO Auto-generated method stub
		
	}

	/*public void execute(ApiRequest apiRequest, ApiWorkflow apiWorkflow) {
		try{
			if(apiRequest == null || apiWorkflow == null || apiWorkflow.getApiPreProcessor() == null){
				throw new ApiException("Test Exception");
			}
			if(apiWorkflow.getApiPreProcessor().validate(apiRequest)){
				AggregateData aggregateData = apiWorkflow.getApiProcessor().process(apiRequest);
				response = apiWorkflow.getApiPostProcessor().postPorcessor(aggregateData);
				return response;
			}			
		}
		catch(ApiException ae){
			System.out.println(ae);
			response= apiWorkflow.getApiErrorProcessor().processError(apiRequest, ae);
		}
		return response;
	}*/

}
