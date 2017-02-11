package com.halal.sa.data.dao.impl;


import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static com.halal.sa.core.exception.ErrorConstants.ERRORDESC_MONGODB_UNAVAILABLE;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.halal.sa.common.ApplicationConstant;
import com.halal.sa.core.exception.ApiException;
import com.halal.sa.core.exception.ErrorConstants;
import com.halal.sa.data.dao.BusinessDao;
import com.halal.sa.data.entities.Business;
import com.halal.sa.service.ThirdPartyService;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Repository("businessDaoImpl")
public class BusinessDaoImpl implements BusinessDao{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BusinessDaoImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	ThirdPartyService thirdPartyService;
	
	

	/**
	 * This method adds restaurant info in DB
	 * @throws ApiException 
	 */
	public void addBusinessInfo(Business business) throws ApiException {
		try{
			mongoTemplate.save(business);
			LOGGER.info("Added the business in the DB - "+business.getName());
		}
		catch(Exception e){
			LOGGER.error("ERR_MONGODB_UNAVAILABLE", e);
			throw new ApiException("ERR_MONGODB_UNAVAILABLE", ERRORDESC_MONGODB_UNAVAILABLE);
		}
	}

	/**
	 * This method updates restaurant info in DB
	 */
	public void updateBusinessInfo(Business business) {
		// TODO Auto-generated method stub
	}
	
	public List searchBusinessByKeyword(String keyword, List ids) throws ApiException {
		if(ids == null){
			LOGGER.error("No ids found in search by Lat Lng");
			throw new ApiException(ErrorConstants.ERRCODE_RECORD_NOT_FOUND, "No ids found in search by Lat Lng");
		}
		LOGGER.info("Searching the business with keyword - "+keyword+", in ids count -"+ids.size());
		int skip = 0;
		int recordsPerPage = ApplicationConstant.BUSINESS_RECORDS_PER_PAGE;
		int extraPaginationrecord = ApplicationConstant.BUSINESS_PAGINATION_EXTRA_RECORD;
		
		BasicDBObject matchSearch = new BasicDBObject("$match", new BasicDBObject("$text", new BasicDBObject("$search", keyword)));
		
		BasicDBObject matchIds = new BasicDBObject("$match", new BasicDBObject("_id", new BasicDBObject("$in", ids)));
		
		List<DBObject> aggregationList = new ArrayList<DBObject>();
		aggregationList.add(matchSearch);
		aggregationList.add(matchIds);
		try{
			AggregationOutput aggregationOutput = mongoTemplate.getCollection("business").aggregate(aggregationList);
		
		
		
		List<DBObject> dbObjects = (List<DBObject>) aggregationOutput.results();
		LOGGER.info("Returning the ids searched by keyword - "+dbObjects.size());
		return dbObjects;
		}
		catch(Exception e){
			LOGGER.error("ERR_MONGODB_UNAVAILABLE", e);
			throw new ApiException("ERR_MONGODB_UNAVAILABLE", ERRORDESC_MONGODB_UNAVAILABLE);
		}
	}
	
	/**
	 * This method gets the business list near the passed lat, lng, distance
	 * @param longitude
	 * @param latitude
	 * @param radius
	 * @param distanceUnit
	 * @param categoty
	 * @return
	 */
	public List<DBObject> searchBusinessByLocation(double longitude, double latitude, double radius, String distanceUnit, String categoty) {
		LOGGER.info("Searching the business by longitude :"+longitude+", and Latitude :"+latitude);
		Metrics metrics = Metrics.MILES;
		if(null != distanceUnit && distanceUnit.equalsIgnoreCase(ApplicationConstant.KILOMETRES)){
			metrics = Metrics.KILOMETERS;
		}
		Aggregation  aggregation;
		NearQuery geoNear = NearQuery.near(longitude,latitude, metrics).maxDistance(radius);
		
		if(categoty !=null){
			aggregation = newAggregation(Aggregation.geoNear(geoNear, "distance"), Aggregation.match(Criteria.where("category").in(categoty)));
		}else{
			aggregation = newAggregation(Aggregation.geoNear(geoNear, "distance"));
		}
		
		AggregationResults<DBObject> groupResults = mongoTemplate.aggregate(aggregation, Business.class, DBObject.class);
		
		List<DBObject> result = groupResults.getMappedResults();
		LOGGER.info("Returning the ids searched by Longitude :"+longitude+", and Latitude :"+latitude+", result count - "+result.size());
		return result;
	}
	
	/**
	 * This method gets the business count for each category near passed lat, lng, distance
	 * @param longitude
	 * @param latitude
	 * @param distance
	 * @param distanceUnit
	 * @param categoty
	 * @return
	 */
	public List searchBusinessCategories(double longitude, double latitude, Double distance, String distanceUnit, String categoty) {
		LOGGER.info("Searching the business by longitude :"+longitude+", and Latitude :"+latitude);
		Metrics metrics = Metrics.MILES;
		if(null != distanceUnit && distanceUnit.equalsIgnoreCase("km")){
			metrics = Metrics.KILOMETERS;
		}
		Aggregation  aggregation;
		NearQuery geoNear = NearQuery.near(longitude,latitude, metrics).maxDistance(distance);
		
		
		
		//lookup in business_info to get the imageUrl of all cat, here _id is category collumn of group by result which is maped to name column of business_info doc
		aggregation = newAggregation(Aggregation.geoNear(geoNear, "distance"), Aggregation.group("category").count().as("count"));//, Aggregation.lookup("business_info", "_id", "name", "data")); // This might required to limit the records - ,	
		AggregationResults<DBObject> groupResults = mongoTemplate.aggregate(aggregation, Business.class, DBObject.class);
		
		List<DBObject> result = groupResults.getMappedResults();
		LOGGER.info("Returning the ids searched by Longitude :"+longitude+", and Latitude :"+latitude+", result count - "+result.size());
		return result;
	}
	
//	@Query(value = "{'name': {$regex : '^?0$', $options: 'i'}}")
//	public Business searchBusinessProfile(String name, String locality, String city){
//		if(name.contains("-")){
//			name = name.replace("-", " ");
//		}
//		String localityQuery="";
//		if(!StringUtils.isBlank(locality)){
//			localityQuery = ", locality:'"+locality+"'";
//		}
//		
//		BasicQuery query = new BasicQuery("{name : {$regex : '?0', $"+name+": 'i'}"+localityQuery+", address.city :'"+city+"' }");
//		Business business = mongoTemplate.findOne(query, Business.class);
//		return business;
//	}
	
}
