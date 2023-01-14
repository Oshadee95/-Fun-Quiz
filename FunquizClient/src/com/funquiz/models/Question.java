package com.funquiz.models;

import java.io.Serializable;
import java.net.URL;

/**
 * Question class is used as as model class to transfer and retrieve data from server to client and vice versa
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class Question implements Serializable {

	/**
	 * serialVersionUID of the class
	 */
	private static final long serialVersionUID = -5958632799165011967L;
	
	/**
	 * Id to the question and the user provided solution 
	 */
	private int id, solution = -1;
	
	/**
	 * URL of the image which contains the question
	 */
	private URL url = null; 
	
	/**
	 * Constructor to retrieve the questions for a quiz generation
	 * 
	 * @param location URL of the question image
	 */
	public Question(URL location) {
		this.url = location;
	}
	
	/**
	 * Constructor to remove question from the database 
	 *  
	 * @param id Id of the question to be removed
	 */
	public Question(int id) {
		this.id = id;
	}
	
	/**
	 * Constructor to update a question in the database 
	 * 
	 * @param id Id of the question to be updated
	 * @param url New URL of the question's image
	 */
	public Question(int id, URL url) {
		this.id = id;
		this.url = url;
	}

	/**
	 * Constructor to retrieve a question from the database 
	 * 
	 * @param id Id of the question to be retrieved
	 * @param url URL of the question's image to be retrieved
	 * @param solution Solution for the question
	 */
	public Question(int id, URL url, int solution) {
		this.id = id;
		this.url = url;
		this.solution = solution;
	}
	
	/**
	 * To obtain the id of the question
	 * 
	 * @return Id of the question
	 */
	public int getId() {
		return id;
	}

	/**
	 * To obtain the URL of the question's image
	 * 
	 * @return URL of the question's image
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * To set the URL of the question's image
	 * 
	 * @param url URL of the question's image
	 */
	public void setUrl(URL url) {
		this.url = url;
	}

	/**
	 * To obtain the solution for the question
	 * 
	 * @return Solution for the question
	 */
	public int getSolution() {
		return solution;
	}

	/**
	 * To set the solution for the question
	 * 
	 * @param solution Solution for the question
	 */
	public void setSolution(int solution) {
		this.solution = solution;
	}
}
