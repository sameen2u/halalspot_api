package com.halal.sa.data.dao;

import java.util.List;

import com.halal.sa.data.entities.Business;

public interface BusinessDao {
	
	public void addBusinessInfo(Business business);
	
	public void updateBusinessInfo(Business business);
	
	public List searchBusiness(String keyword,String address,  int distance);

	public List searchBusinessByKeyword(String keyword);
}
