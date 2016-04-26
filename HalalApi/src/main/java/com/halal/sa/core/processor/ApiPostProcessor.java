package com.halal.sa.core.processor;


import java.util.Map;

import com.halal.sa.core.response.AggregateData;

public interface ApiPostProcessor {
	
	public Map<String,Object> postPorcessor(AggregateData aggregateData);

}
