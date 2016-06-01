package com.halal.sa.controller.vo.response;

import java.util.List;

public class SearchBusinessResponse {
	
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
