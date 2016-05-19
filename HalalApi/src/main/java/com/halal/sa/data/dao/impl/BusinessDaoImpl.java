package com.halal.sa.data.dao.impl;


import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Sphere;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.halal.sa.common.ApplicationConstant;
import com.halal.sa.data.dao.BusinessDao;
import com.halal.sa.data.entities.Business;
import com.halal.sa.service.ThirdPartyService;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Repository("businessDaoImpl")
public class BusinessDaoImpl implements BusinessDao{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AccountDaoImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	ThirdPartyService thirdPartyService;

	/**
	 * This method adds restaurant info in DB
	 */
	public void addBusinessInfo(Business business) {
		mongoTemplate.save(business);
	}

	/**
	 * This method updates restaurant info in DB
	 */
	public void updateBusinessInfo(Business business) {
		// TODO Auto-generated method stub
	}
	
	public List businessCountByKeyword(String keyword, List ids) {
		return ids;
	}
	
	public List searchBusinessByKeyword(String keyword, List ids, int skip) {
//		TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(keyword);
//		Query query = TextQuery.queryText(criteria);
//		List<Business> businesses = mongoTemplate.find(query,Business.class);
		
//		Aggregation  aggregation;
//		aggregation = newAggregation(Aggregation.match(Criteria.where("_id").in(ids)));
		
		int recordLimit = ApplicationConstant.BUSINESS_RECORDS_PER_PAGE;
		int extraPaginationrecord = ApplicationConstant.BUSINESS_PAGINATION_EXTRA_RECORD;
		
		BasicDBObject matchSearch = new BasicDBObject("$match", new BasicDBObject("$text", new BasicDBObject("$search", keyword)));
		
		BasicDBObject matchIds = new BasicDBObject("$match", new BasicDBObject("_id", new BasicDBObject("$in", ids)));
		
		BasicDBObject limitRecords = new BasicDBObject("$limit", recordLimit+extraPaginationrecord );
		
		BasicDBObject skipRecords = new BasicDBObject("$skip", skip );
		
//		BasicDBObject project = new BasicDBObject("$project", new BasicDBObject("_id", "$_id"));

		List<DBObject> aggregationList = new ArrayList<DBObject>();
		aggregationList.add(matchSearch);
		aggregationList.add(matchIds);
		aggregationList.add(skipRecords);
		aggregationList.add(limitRecords);
		
		AggregationOutput aggregationOutput = mongoTemplate.getCollection("business")
		        .aggregate(aggregationList);
		
		List<DBObject> dbObjects = (List<DBObject>) aggregationOutput.results();
		return dbObjects;
	}
	
	public List searchBusinessByLocation(double longitude, double latitude, double distance) {
		Aggregation  aggregation;
		
		NearQuery geoNear = NearQuery.near(longitude,latitude, Metrics.KILOMETERS).maxDistance(distance);
		
		//limit and skip are used for pagination logic
		aggregation = newAggregation(Aggregation.geoNear(geoNear, "distance")); // This might required to limit the records - ,	
		AggregationResults<DBObject> groupResults = mongoTemplate.aggregate(aggregation, Business.class, DBObject.class);
		
		List<DBObject> result = groupResults.getMappedResults();
		return result;
		
	}
	
	
	
}
