package com.halal.sa.data.dao;

import java.util.List;

import com.halal.sa.core.exception.ApiException;
import com.halal.sa.data.entities.Business;

public interface BusinessDao {
	
	public void addBusinessInfo(Business business)  throws ApiException;
	
	public void updateBusinessInfo(Business business);
	
//	public List searchBusiness(String keyword,String address,  double distance, int skipRecords);

	public List searchBusinessByKeyword(String keyword, List ids) throws ApiException;
}
