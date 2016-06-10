package com.halal.sa.common.error;

public enum ErrorCode {
	
	ERR_EMAIL_NOT_FOUND ("ERR_EMAIL_NOT_FOUND"),
	
	ERR_COMMUNICATING_WITH_EXTERNAL_SYSTEM ("ERR_COMMUNICATING_WITH_EXTERNAL_SYSTEM"),
	
	ERR_ENCOUNTERED_DURING_PROCESSING ("ERR_ENCOUNTERED_DURING_PROCESSING"),
	
	ERR_SERVICE_UNAVAILABLE ("ERR_SERVICE_UNAVAILABLE"),
	
	ERR_UNAUTHORIZED ("ERR_UNAUTHORIZED"),
	
	ERR_METHOD_NOT_ALLOWED("ERR_METHOD_NOT_ALLOWED"), 
	
	ERR_RECORD_NOT_FOUND ("ERR_RECORD_NOT_FOUND"),
	
	ERR_FETCH_TEMPLATE_UNAVAILABLE ("ERR_FETCH_TEMPLATE_UNAVAILABLE"),
	
	ERR_BAD_REQUEST ("ERR_BAD_REQUEST"),
	
	ERR_GATEWAY_TIMEOUT("ERR_GATEWAY_TIMEOUT"),
	
	ERR_UNSUPPORTED_MEDIA_TYPE("ERR_UNSUPPORTED_MEDIA_TYPE"),
	
	ERR_MONGODB_UNAVAILABLE("ERR_MONGODB_UNAVAILABLE"),
	
	ERR_REQUEST_COULD_NOT_BE_PROCESSED("ERR_REQUEST_COULD_NOT_BE_PROCESSED");
	
	private final String value;

	ErrorCode(String value) {
        this.value = value;
    }

	@Override
    public String toString() {
        return value;
    }
}
