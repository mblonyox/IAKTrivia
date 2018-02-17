package com.mblonyox.iaktrivia.model;

import java.util.List;

public class ResponseTrivia{
	private int responseCode;
	private List<ResultsItem> results;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"ResponseTrivia{" + 
			"response_code = '" + responseCode + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}
}