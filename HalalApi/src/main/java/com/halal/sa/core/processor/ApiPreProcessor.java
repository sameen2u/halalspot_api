package com.halal.sa.core.processor;

import com.halal.sa.common.error.ApiException;
import com.halal.sa.core.ApiRequest;

public interface ApiPreProcessor {

	public boolean validate(ApiRequest apiRequest) throws ApiException;
}
