package com.funquiz.models;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Game class is used as as model class to transfer and retrieve data from server to client and vice versa
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class Game implements Serializable {
	
	/**
	 * serialVersionUID of the class
	 */
	private static final long serialVersionUID = -1107462925489846651L;
	
	/**
	 * id and the size of the paticular quiz 
	 */
	private int id, quizSize;
	
	/**
	 * Username of the player and the results of the quiz concatinated to a string
	 */
	private String playerUName, quizResults;
	
	/**
	 * Final score of the quiz
	 */
	private float quizScore;
	
	/**
	 * Timestamp of which the player submitted the quiz
	 */
	private Timestamp playedDate;

	/**
	 * Constructor to retrieve the players and their scores to create the leaderboard
	 * 
	 * @param quizSize Size of the questionnaire taken
	 * @param playerUName Username of the player who has taken the quiz
	 * @param quizScore Final score obtained a the total of correct solutions
	 */
	public Game(int quizSize, String playerUName, float quizScore) {
		this.quizSize = quizSize;
		this.playerUName = playerUName;
		this.quizScore = quizScore;
	}
	
	/**
	 * Constructor to create database entry in a quiz submission
	 * 
	 * @param quizSize Size of the questionnaire taken
	 * @param playerUName Username of the player who has taken the quiz
	 * @param quizResults Results of the quiz concatinated to a string
	 * @param quizScore Final score obtained a the total of correct solutions
	 */
	public Game(int quizSize, String playerUName, String quizResults, float quizScore) {
		this.quizSize = quizSize;
		this.playerUName = playerUName;
		this.quizResults = quizResults;
		this.quizScore = quizScore;
	}
	
	/**
	 *  Constructor to retrieve the player's previous quizes
	 * 
	 * @param quizSize Size of the questionnaire taken
	 * @param quizResults Results of the quiz concatinated to a string
	 * @param quizScore Final score obtained a the total of correct solutions
	 * @param playedDate Date of which the player submitted the quiz
	 */
	public Game(int quizSize, String quizResults, float quizScore, Timestamp playedDate) {
		this.quizSize = quizSize;
		this.quizResults = quizResults;
		this.quizScore = quizScore;
		this.playedDate = playedDate;
	}

	/**
	 * To obtain the Id of the retrieved quiz 
	 * 
	 * @return Id of the retrieved quiz 
	 */
	public int getId() {
		return id;
	}

	/**
	 * To obtain the size of the questionnaire taken/submitted
	 * 
	 * @return Size of the questionnaire taken/submitted
	 */
	public int getQuizSize() {
		return quizSize;
	}

	/**
	 * To obtain the username of the player who has taken/submitted the quiz
	 * 
	 * @return Username of the player who has taken/submitted the quiz
	 */
	public String getPlayerUName() {
		return playerUName;
	}

	/**
	 * To obtain the results of the quiz concatinated to a string
	 * 
	 * @return Results of the quiz concatinated to a string
	 */
	public String getQuizResults() {
		return quizResults;
	}

	/**
	 * To obtain the final score obtained a the total of correct solutions
	 * 
	 * @return Final score obtained a the total of correct solutions
	 */
	public float getQuizScore() {
		return quizScore;
	}

	/**
	 * To obtain the timestamp of when the quiz was submitted to obtain results
	 * 
	 * @return Timestamp of when the quiz was submitted to obtain results
	 */
	public Timestamp getPlayedDate() {
		return playedDate;
	}
}
