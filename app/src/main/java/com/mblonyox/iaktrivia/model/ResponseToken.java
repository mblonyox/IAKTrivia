package com.mblonyox.iaktrivia.model;

import com.google.gson.annotations.SerializedName;

public class ResponseToken{

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("response_message")
	private String responseMessage;

	@SerializedName("token")
	private String token;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setResponseMessage(String responseMessage){
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage(){
		return responseMessage;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"ResponseToken{" + 
			"response_code = '" + responseCode + '\'' + 
			",response_message = '" + responseMessage + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}