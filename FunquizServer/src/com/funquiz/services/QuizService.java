package com.funquiz.services;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.funquiz.models.Game;
import com.funquiz.models.Question;
import com.funquiz.models.QuestionReport;
import com.funquiz.models.QuizReport;
import com.funquiz.models.User;

/**
 * QuizService to execute operations related questionnaires taken by the a player using the RMI Client
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class QuizService {

	QuestionService questionService = new QuestionService();
	
	/**
	 * To retrieve random quiz(questionnaire) based on a specified size
	 * 
	 * @param questionnaireSize To obtain size of the questionnaire that needs to retrieved
	 * @return List of questions which is limited by the questionnaireSize param
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	public List<Question> getQuestionnaire(int questionnaireSize) throws ClassNotFoundException, SQLException, MalformedURLException {
		return new QuestionService().getByCount(questionnaireSize);
	}
	
	/**
	 * To retrieve the results and total score based on the taken quiz
	 * 
	 * @param user To obtain the username of the user who has taken the quiz
	 * @param questionnaire List of questions which contains the question and the selected solution
	 * @return List of QuestionReports and the score to the specific taken quiz
	 * @throws NumberFormatException When an attempt is made to convert a string with improper format into a numeric value
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public QuizReport getQuizResults(User user, List<Question> questionnaire) throws NumberFormatException, ClassNotFoundException, SQLException {
		QuizReport quizReport = new QuizReport();
		String quizResultsAsString = "";
		List<QuestionReport> questionReports = new ArrayList<QuestionReport>();
		float totalCorrectSolutions = 0;
		int index = 1;
		for (Question question : questionnaire) {
			if (question.getSolution() == -1) {
				questionReports.add(new QuestionReport(index, "Skipped"));
				quizResultsAsString += index + "-Skipped,";
			} else if (checkSolution(question) == true) {
				questionReports.add(new QuestionReport(index, "Correct"));
				quizResultsAsString += index + "-Correct,";
				totalCorrectSolutions++;
			} else {
				questionReports.add(new QuestionReport(index, "Incorrect"));
				quizResultsAsString += index + "-Incorrect,";
			}
			index++;
		}
		quizReport.setQuestionReports(questionReports);
		quizReport.setScore(calculateScore(index - 1, totalCorrectSolutions));

		GameService gameService = new GameService();
		gameService.add(new Game(questionnaire.size(), user.getUserName(), quizResultsAsString, Float.parseFloat(quizReport.getScore())));
		return quizReport;
	}

	/**
	 * To validate the user provided solution against the correct solution
	 * 
	 * @param question To obtain the user provided solution
	 * @return Whether provided solution was correct (true) or not (false)
	 */
	private boolean checkSolution(Question question) {
		return question.getSolution() == questionService.getSolution(question.getUrl().toString());
	}

	/**
	 * To retrieve the quiz score based on the provided correct solutions
	 * 
	 * @param questionnaireSize Size of the questionnaire 
	 * @param totalCorrectSolutions Number of correct solutions provided by the user
	 * @return Total score by dividing the correct solutions by the questionnaire size
	 */
	private String calculateScore(int questionnaireSize, float totalCorrectSolutions) {
		float score = (totalCorrectSolutions / questionnaireSize) * 100;
		return new DecimalFormat("#.00").format(score);
	}
}
