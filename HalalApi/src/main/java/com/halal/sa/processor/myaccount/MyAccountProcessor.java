package com.halal.sa.processor.myaccount;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.halal.sa.common.ApplicationConstant;
import com.halal.sa.common.CommonUtil;
import com.halal.sa.common.error.ApiException;
import com.halal.sa.controller.vo.response.SearchBusiness;
import com.halal.sa.controller.vo.response.SearchReport;
import com.halal.sa.core.AbstractProcessor;
import com.halal.sa.core.AggregateData;
import com.halal.sa.core.ApiRequest;
import com.halal.sa.core.RequestParameters;
import com.halal.sa.core.request.SearchRequestParameters;
import com.halal.sa.data.dao.impl.BusinessDaoImpl;
import com.halal.sa.data.entities.Address;
import com.halal.sa.service.ThirdPartyService;
import com.mongodb.DBObject;

@Component
public class MyAccountProcessor extends AbstractProcessor{
	
	String KEYWORD=null;
	int PAGE=0;
	
	@Autowired
	BusinessDaoImpl businessDaoImpl;
	
	@Autowired
	ThirdPartyService thirdPartyService;
	

	@Override
	public AggregateData retrieveData(RequestParameters requestParameters)
			throws ApiException {
		SearchRequestParameters searchRequestParameters = (SearchRequestParameters) requestParameters;
//		MyAccountAggregateData searchBusinessAggregateData = searchbusinesses(searchRequestParameters);
		return null;
	}
	
	

}
