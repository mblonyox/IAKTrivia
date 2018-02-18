package com.mblonyox.iaktrivia.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTrivia{

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("results")
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