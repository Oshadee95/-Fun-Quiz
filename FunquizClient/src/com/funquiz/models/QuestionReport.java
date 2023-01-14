package com.funquiz.models;

import java.io.Serializable;

/**
 * QuestionReport class is used as as model class to transfer and retrieve data from server to client and vice versa
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class QuestionReport implements Serializable {

	/**
	 * serialVersionUID of the class
	 */
	private static final long serialVersionUID = -8656149897594448726L;
	
	/**
	 * Question id of the question
	 */
	private int questionId = -1;
	
	/**
	 * Results of the question (Correct, Incorrect or Skipped)
	 */
	private String result = "";

	/**
	 * Constructor to gerenate the results when creating a quiz report
	 * 
	 * @param questionId Id of the question (incremented on the runtime) 
	 * @param result Result of the provided solution (Correct, Incorrect or Skipped)
	 */
	public QuestionReport(int questionId, String result) {
		super();
		this.questionId = questionId;
		this.result = result;
	}

	/**
	 * To obtain the id of the question
	 * 
	 * @return Id of the question
	 */
	public int getQuestionId() {
		return questionId;
	}
	
	/**
	 * To obtain the rsult of the provided solution (Correct, Incorrect or Skipped)
	 * 
	 * @return Result of the provided solution (Correct, Incorrect or Skipped)
	 */
	public String getResult() {
		return result;
	}
}
