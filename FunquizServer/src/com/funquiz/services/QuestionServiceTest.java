package com.funquiz.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.funquiz.models.Question;

/**
 * Test class to execute and validate operations related to QuestionService
 * 
 * @author Oshadee Amarasinghe
 *
 */
@TestMethodOrder(OrderAnnotation.class)
class QuestionServiceTest {

	/**
	 * To test question creation
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	@Test
	@Order(1)
	void questionAddTest() throws ClassNotFoundException, SQLException, MalformedURLException {
		QuestionService questionService = new QuestionService();
		Question question = new Question(new URL("https://sanfoh.com/heartgame/sixeqgame_1000.png"));
		assertTrue(questionService.add(question));
	}

	/**
	 * To test specific question update
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	@Test
	@Order(2)
	void questionUpdateTest() throws ClassNotFoundException, SQLException, MalformedURLException {
		QuestionService questionService = new QuestionService();
		Question question = new Question(1001, new URL("https://sanfoh.com/heartgame/sixeqgame_1001.png"));
		assertTrue(questionService.update(question));
	}

	/**
	 * To test specific question retrieval
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	@Test
	@Order(3)
	void questionGetTest() throws ClassNotFoundException, SQLException, MalformedURLException {
		QuestionService questionService = new QuestionService();
		Question question = questionService.get(new Question(1001));
		String dbQuestionURL = question.getUrl().toString();
		assertEquals(1001, question.getId());
		assertEquals("https://sanfoh.com/heartgame/sixeqgame_1001.png", dbQuestionURL);
		assertEquals(1, question.getSolution());
	}

	/**
	 * To test specific question removal
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	@Test
	@Order(4)
	void questionRemoveTest() throws ClassNotFoundException, SQLException, MalformedURLException {
		QuestionService questionService = new QuestionService();
		Question question = new Question(1001);
		assertTrue(questionService.remove(question));
	}

	/**
	 * To test all question retrieval
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	@Test
	@Order(5)
	void questionGetAllTest() throws ClassNotFoundException, SQLException, MalformedURLException {
		QuestionService questionService = new QuestionService();
		List<Question> questionList = questionService.getAll();
		assertEquals(1000, questionList.size());
	}

	/**
	 * To test retrieval of questions by specific count
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	@Test
	@Order(6)
	void questionGetByCountTest() throws ClassNotFoundException, SQLException, MalformedURLException {
		QuestionService questionService = new QuestionService();
		List<Question> questionList = questionService.getByCount(10);
		assertEquals(10, questionList.size());
	}

	/**
	 * To test solution for a specific question
	 * 
	 * @throws ClassNotFoundException Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	@Test
	@Order(7)
	void questionCheckSolutionTest() throws ClassNotFoundException, SQLException, MalformedURLException {
		QuestionService questionService = new QuestionService();
		int solution = questionService.getSolution("https://sanfoh.com/heartgame/sixeqgame_1000.png");
		assertEquals(0, solution);
	}
}
