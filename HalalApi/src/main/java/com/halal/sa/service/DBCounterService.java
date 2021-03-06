package com.halal.sa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.halal.sa.data.entities.Counters;

import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.*;

/**
 * This class is responsible for getting next sequence id for profile id of a business
 * @author SXH8750
 *
 */
@Service
public class DBCounterService {
	
  @Autowired 
  private MongoTemplate mongo;
   
  public int getNextSequence(String collectionName) {
    Counters counters = mongo.findAndModify(
      query(where("_id").is(collectionName)), 
      new Update().inc("seq", 1),
      options().returnNew(true),
      Counters.class);
       
    return counters.getSeq();
  }
}