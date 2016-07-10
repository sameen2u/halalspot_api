package com.halal.sa.data.dao;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.Repository;

import com.halal.sa.data.entities.Business;

public interface SearchBusinessDao extends Repository<Business, Serializable>{

	  @Query(value="{'address.city' : {$regex : '^?0$', $options: 'i'}, 'profile_id' : ?1}")
	   public Business findByBusinessCodeAndProfileId(String city, int profile_id);
}

