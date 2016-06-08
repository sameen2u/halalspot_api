package com.halal.sa.processor.searchbusiness;

import org.springframework.stereotype.Component;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.core.AbstractPostProcessor;
import com.halal.sa.core.AbstractProcessor;
import com.halal.sa.core.AggregateData;
import com.halal.sa.core.RequestParameters;

@Component
public class SearchBusinessPostProcessor extends AbstractPostProcessor{

	@Override
	public <T> T transform(RequestParameters requestParameters,
			AggregateData aggregateData) throws ApiException {
		// TODO Auto-generated method stub
		return (T) aggregateData;
	}

	

}
