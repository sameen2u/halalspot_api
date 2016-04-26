package com.halal.sa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.halal.sa.data.dao.BusinessDao;
import com.halal.sa.data.dao.impl.BusinessDaoImpl;

@Service
public class SearchBusinessService extends BaseService{
	
	@Autowired
	BusinessDaoImpl businessDaoImpl;

	@Override
	protected Object processResponse(Object model, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean validate(Object model) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Object searchbusinesses(String keyword, String address, int distance){
		List businesses = businessDaoImpl.searchBusiness(keyword, address, distance);
		return processResponseEntity(businesses, HttpStatus.OK);
	}

}
