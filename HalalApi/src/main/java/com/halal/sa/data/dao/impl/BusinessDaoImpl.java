package com.halal.sa.data.dao.impl;


import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.ArrayList;
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

import com.halal.sa.data.dao.BusinessDao;
import com.halal.sa.data.entities.Business;
import com.halal.sa.service.ThirdPartyService;
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
	
	/**
	 * This is the main method returns the list of business based on the address and keyword if passed 
	 */
	public List searchBusiness(String keyword, String address, int distance) {
		List businessIds = null;
		double longitude;
		double latitude;
		if(StringUtils.isNotEmpty(keyword)){
			businessIds = searchBusinessByKeyword(keyword);
		}
		Map<String,Double> map = thirdPartyService.getLongiLatitude(address);
		if(map.size() >0){
			longitude = map.get("lng");
			latitude = map.get("lat");
			return searchBusinessByLocation(longitude, latitude, distance, businessIds);
		}
		return null;		
		
		/*// TODO 6371 km or 3959 miles
		Query query = new Query();
		//always pass radius as 2.0 or 4.0 to get proper response
		Circle circle = new Circle(73.847465    ,18.530822, 3.0 /6371);
		Sphere sphere = new Sphere(circle);
//		List<Business> businesses = mongoTemplate.find(query(where("location").withinSphere(circle)), Business.class);
		List<Business> businesses = mongoTemplate.find(query.addCriteria(Criteria.where("address.location").within(sphere)),Business.class);
		return businesses;*/
	}
	
	public List searchBusinessByKeyword(String keyword) {
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(keyword);
		Query query = TextQuery.queryText(criteria);
		List<Business> businesses = mongoTemplate.find(query,Business.class);
		return businesses;
	}
	
	public List searchBusinessByLocation(double longitude, double latitude, double distance, List ids) {
		Aggregation  aggregation;
		NearQuery geoNear = NearQuery.near(longitude,latitude, Metrics.KILOMETERS).num(10).maxDistance(distance);
		if(ids!=null && ids.size()>0){
			aggregation = newAggregation(Aggregation.geoNear(geoNear, "distance"), Aggregation.match(Criteria.where("_id").in(ids)));
		}
		else{
			aggregation = newAggregation(Aggregation.geoNear(geoNear, "distance"));	
		}
		AggregationResults<DBObject> groupResults = mongoTemplate.aggregate(aggregation, Business.class, DBObject.class);
		
		List<DBObject> result = groupResults.getMappedResults();
		return result;
		
	}
	
}
