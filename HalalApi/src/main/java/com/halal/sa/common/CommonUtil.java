package com.halal.sa.common;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.halal.sa.data.dao.impl.MyAccountDaoImpl;

public class CommonUtil {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);
	
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final ObjectReader reader = mapper.reader(HashMap.class);
	
	public String buildUrl(String uri){
		return ApiConstant.API_HOST+":"+ApiConstant.API_PORT+uri;
	}
	
	/**
	 * This method will hash the password and abstract it from being stolen
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashPassword(String password) throws NoSuchAlgorithmException{
		if(password != null){
			MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(password.getBytes());
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
			return sb.toString();
		}
		return null;
	}
	
	/**
	 * It encode the String with Base64 methodology
	 * @param inputString
	 * @return
	 */
	public static String encriptString(String inputString){
		if(StringUtils.isEmpty(inputString)){
			return null;
		}
		byte[]   bytesEncoded = Base64.encodeBase64(inputString.getBytes());
		return new String(bytesEncoded);
	}
	
	/**
	 * It decode the String with Base64 methodology
	 * @param inputString
	 * @return
	 */
	public static String decryptString(String inputString){
		if(StringUtils.isEmpty(inputString)){
			return null;
		}
		byte[] valueDecoded= Base64.decodeBase64(inputString);
		return new String(valueDecoded);
	}
	
	/**
	 * This method will help to convert the JSON string to Map object.
	 *  
	 * @param documentumContent
	 * @return
	 */
	public static Map<String,Object> convertJsonStringToMap(String jsonStr){
		Map<String,Object> mapObj = null;
		if(!StringUtils.isEmpty(jsonStr)){
			try {
				mapObj = reader.readValue(jsonStr);
			}catch (Exception e) {
				LOGGER.debug("Exception while converting json to Map.");
				e.printStackTrace();
			}
		}
		return mapObj;
	}
	
	/**
	 * This will convert the Map object to JSON string.
	 * 
	 * @param mabObj
	 * @return
	 */
	public static String convertMapToJsonString(Map<String, Object> mabObj) {
		String jsonStr = null;
		if(null!=mabObj)
			try {
				jsonStr = mapper.writeValueAsString(mabObj);
			}catch (Exception e) {
				LOGGER.debug("Exception while converting Map to JSON String..");
			}
		return jsonStr; 
	}
	
	/**
	 * This will Convert the String decimal value double value. if any errors, return 0.0 by defaults
	 * @param str
	 * @return
	 */
	public static double convertStringToDecimal(String str){
		double val=0.0;
		if(StringUtils.isBlank(str)){
			return val;
		}
		try{
			val = Double.valueOf(str); 
		}catch(Exception e){
			LOGGER.error("convertStringToDecimal. Error ["+str+"] while converting to Decimal.."+e);
		}
		return val;
	}
	/**
	 * This will Convert the String integer value double value. if any errors, return 0 by defaults
	 * @param str
	 * @return
	 */
	public static int convertStringToInt(String str){
		int val=0;
		if(StringUtils.isBlank(str)){
			return val;
		}
		try{
			val = Integer.valueOf(str); 
		}catch(Exception e){
			LOGGER.error("convertStringToInt. Error ["+str+"] while converting to Integer.."+e);
		}
		return val;
	}
	
	public static int convertStringToInt(Object str){
		int val=0;
		try{
			if(null!=str){
				val = Integer.valueOf(str.toString()); 
			}
		}catch(Exception e){
			LOGGER.error("convertStringToInt. Error ["+str+"] while converting to Integer.."+e);
		}
		return val;
	}
	
	/**
	 * This will Convert the String decimal value double value. if any errors, return 0.0 by defaults
	 * @param str
	 * @return
	 */
	public static double convertStringToDecimal(Object str){
		double val=0.0;
		try{
			if(null!=str){
				val = Double.valueOf(str.toString()); 
			}
		}catch(Exception e){
			LOGGER.error("convertStringToDecimal. Error ["+str+"] while converting to Decimal.."+e);
		}
		return val;
	}
	
	/**
	 * This will Convert the String Log value double value. if any errors, return 0.0 by defaults
	 * @param str
	 * @return
	 */
	public static long convertStringToLong(Object str){
		long val=0;
		try{
			if(null!=str){
				val = Long.valueOf(str.toString()); 
			}
		}catch(Exception e){
			LOGGER.error("convertStringToLong. Error ["+str+"] while converting to Long.."+e);
		}
		return val;
	}

	
}
