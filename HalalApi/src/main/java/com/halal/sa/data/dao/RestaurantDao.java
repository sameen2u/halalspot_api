package com.halal.sa.data.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.Repository;

import com.halal.sa.data.entities.BusinessInfo;


public interface RestaurantDao extends Repository<BusinessInfo, Serializable>{
	
	//No need to use city for keyword search of cuisine and dishes
	 @Query(value="{name: {$regex : '?0', $options: 'i'}, type: 'cuisine', inuse : 'y'}")
	   public List<BusinessInfo> findCuisineKeywords(String keywordInitial);

}
