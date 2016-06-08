package com.halal.sa.processor.searchbusiness;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.halal.sa.controller.vo.response.SearchBusiness;
import com.halal.sa.controller.vo.response.SearchReport;
import com.halal.sa.core.AggregateData;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchBusinessAggregateData implements AggregateData{
	
	private SearchReport searchReport;
	
	private List<SearchBusiness> searchBusinesses;
	
	public SearchReport getSearchReport() {
		return searchReport;
	}

	public void setSearchReport(SearchReport searchReport) {
		this.searchReport = searchReport;
	}

	public List<SearchBusiness> getSearchBusinesses() {
		return searchBusinesses;
	}

	public void setSearchBusinesses(List<SearchBusiness> searchBusinesses) {
		this.searchBusinesses = searchBusinesses;
	}

}
