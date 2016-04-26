package com.halal.sa.core.processor;

import java.util.List;

import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.response.AggregateData;

public interface ApiProcessor {

	public AggregateData process(ApiRequest apiRequest);
}
