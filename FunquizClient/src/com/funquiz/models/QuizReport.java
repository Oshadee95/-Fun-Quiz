package com.funquiz.models;

import java.io.Serializable;
import java.util.List;

/**
 * QuizReport class is used as as model class to transfer and retrieve data from
 * server to client and vice versa
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class QuizReport implements Serializable {

	/**
	 * serialVersionUID of the class
	 */
	private static final long serialVersionUID = -6631140431350933668L;

	/**
	 * List of QuestionReports which includes the checked solutions against provided solutions
	 */
	private List<QuestionReport> questionReports;
	
	/**
	 * Final score of the quiz 
	 */
	private String score;

	/**
	 * To obtain the list of QuestionReports validated against correct solutions
	 * 
	 * @return List of QuestionReports validated against provided solutions with correct solutions
	 */
	public List<QuestionReport> getQuestionReports() {
		return questionReports;
	}

	/**
	 * To set a list of QuestionReports validated against provided solutions with correct solutions
	 * 
	 * @param questionReports Contains the list of questions validated against provided solutions with correct solutions
	 */
	public void setQuestionReports(List<QuestionReport> questionReports) {
		this.questionReports = questionReports;
	}

	/**
	 * To obtain the score of the taken quiz 
	 * 
	 * @return Total score of the taken quiz 
	 */
	public String getScore() {
		return score;
	}

	/**
	 * To set the score of the taken quiz
	 * 
	 * @param score Total score of the taken quiz 
	 */
	public void setScore(String score) {
		this.score = score;
	}
}
