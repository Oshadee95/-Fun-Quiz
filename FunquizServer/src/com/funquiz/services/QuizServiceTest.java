package com.funquiz.services;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.funquiz.models.Question;
import com.funquiz.models.QuestionReport;
import com.funquiz.models.QuizReport;
import com.funquiz.models.User;

/**
 * Test class to execute and validate operations related to QuizService
 * 
 * @author Oshadee Amarasinghe
 *
 */
class QuizServiceTest {

	/**
	 * To test questionnaire retrieval
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	void getQuestionnaireTest() throws ClassNotFoundException, MalformedURLException, SQLException {
		QuizService quizService = new QuizService();
		int questionnaireSize = 10;
		assertEquals(10, quizService.getQuestionnaire(questionnaireSize).size());
	}

	/**
	 * To test all correct solutions
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	void getQuizResultsTest1() throws ClassNotFoundException, MalformedURLException, SQLException {
		QuizService quizService = new QuizService();
		User user = new User("redhawk");
		List<Question> questionnaire = new ArrayList<Question>();

		for (int i = 0; i < 10; i++) {
			Question question = new Question(new URL("https://sanfoh.com/heartgame/sixeqgame_" + i + ".png"));
			question.setSolution(i);
			questionnaire.add(question);
		}

		QuizReport quizReport = quizService.getQuizResults(user, questionnaire);

		for (QuestionReport question : quizReport.getQuestionReports()) {
			assertEquals("Correct", question.getResult());
		}

		assertEquals(100.0, Float.parseFloat(quizReport.getScore()));
	}

	/**
	 * To test all incorrect solutions
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	void getQuizResultsTest2() throws ClassNotFoundException, MalformedURLException, SQLException {
		QuizService quizService = new QuizService();
		User user = new User("redhawk");
		List<Question> questionnaire = new ArrayList<Question>();

		for (int i = 0; i < 10; i++) {
			Question question = new Question(new URL("https://sanfoh.com/heartgame/sixeqgame_" + i + ".png"));
			question.setSolution(i + 1);
			questionnaire.add(question);
		}

		QuizReport quizReport = quizService.getQuizResults(user, questionnaire);

		for (QuestionReport question : quizReport.getQuestionReports()) {
			assertEquals("Incorrect", question.getResult());
		}

		assertEquals(0.0, Float.parseFloat(quizReport.getScore()));
	}
	
	/**
	 * To test all skipped solutions
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	void getQuizResultsTest3() throws ClassNotFoundException, MalformedURLException, SQLException {
		QuizService quizService = new QuizService();
		User user = new User("redhawk");
		List<Question> questionnaire = new ArrayList<Question>();

		for (int i = 0; i < 10; i++) {
			Question question = new Question(new URL("https://sanfoh.com/heartgame/sixeqgame_" + i + ".png"));
			question.setSolution(-1);
			questionnaire.add(question);
		}

		QuizReport quizReport = quizService.getQuizResults(user, questionnaire);

		for (QuestionReport question : quizReport.getQuestionReports()) {
			assertEquals("Skipped", question.getResult());
		}

		assertEquals(0.0, Float.parseFloat(quizReport.getScore()));
	}

	/**
	 * To test random solutions
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	void getQuizResultsTest4() throws ClassNotFoundException, MalformedURLException, SQLException {
		QuizService quizService = new QuizService();
		User user = new User("redhawk");
		List<Question> questionnaire = new ArrayList<Question>();

		for (int i = 0; i < 10; i++) {
			Question question = new Question(new URL("https://sanfoh.com/heartgame/sixeqgame_" + i + ".png"));
			if (i % 2 == 0) {
				question.setSolution(i);
			} else {
				question.setSolution(i + 1);
			}
			questionnaire.add(question);
		}

		QuizReport quizReport = quizService.getQuizResults(user, questionnaire);

		for (int j = 0; j < quizReport.getQuestionReports().size(); j++) {
			if (j % 2 == 0) {
				assertEquals("Correct", quizReport.getQuestionReports().get(j).getResult());
			} else {
				assertEquals("Incorrect", quizReport.getQuestionReports().get(j).getResult());
			}
		}
		assertEquals(50.0, Float.parseFloat(quizReport.getScore()));
	}
}
