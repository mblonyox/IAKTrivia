package com.mblonyox.iaktrivia.model;

import java.util.List;

public class ResultsItem{
	private String difficulty;
	private String question;
	private String correctAnswer;
	private List<String> incorrectAnswers;
	private String category;
	private String type;

	public void setDifficulty(String difficulty){
		this.difficulty = difficulty;
	}

	public String getDifficulty(){
		return difficulty;
	}

	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public void setCorrectAnswer(String correctAnswer){
		this.correctAnswer = correctAnswer;
	}

	public String getCorrectAnswer(){
		return correctAnswer;
	}

	public void setIncorrectAnswers(List<String> incorrectAnswers){
		this.incorrectAnswers = incorrectAnswers;
	}

	public List<String> getIncorrectAnswers(){
		return incorrectAnswers;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"difficulty = '" + difficulty + '\'' + 
			",question = '" + question + '\'' + 
			",correct_answer = '" + correctAnswer + '\'' + 
			",incorrect_answers = '" + incorrectAnswers + '\'' + 
			",category = '" + category + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}