package com.mblonyox.iaktrivia.model;

import java.util.List;

public class ResponseCategories{
	private List<TriviaCategoriesItem> triviaCategories;

	public void setTriviaCategories(List<TriviaCategoriesItem> triviaCategories){
		this.triviaCategories = triviaCategories;
	}

	public List<TriviaCategoriesItem> getTriviaCategories(){
		return triviaCategories;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCategories{" + 
			"trivia_categories = '" + triviaCategories + '\'' + 
			"}";
		}
}