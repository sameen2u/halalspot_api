package com.halal.sa.data.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.Repository;

import com.halal.sa.data.entities.Business;

public interface SearchBusinessDao extends Repository<Business, Serializable>{

	//this method will get the profile of the business based on the city and profile id
	 @Query(value="{'address.city' : {$regex : '^?0$', $options: 'i'}, 'profile_id' : ?1}")
	   public Business findByBusinessCodeAndProfileId(String city, int profile_id);
	  
	  
	  @Query(value = "{'address.city' :{ '$regex' : '?0', $options: 'i'}, 'name': { '$regex' : '?1', $options: 'i'}}")
	  public List<Business> searchKeywordBusinessName(String city, String keywordInitial);
	  
}

