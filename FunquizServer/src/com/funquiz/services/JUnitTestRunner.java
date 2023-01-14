package com.funquiz.services;

import org.junit.jupiter.api.Test;

/**
 * Test class to execute and test operations related to DB, Game,
 * IPRegistry, Login, Question, Quiz and User Services
 * 
 * @author Oshadee Amarasinghe
 *
 */
class JUnitTestRunner {

	/**
	 * To execute and test operations related to DBService
	 */
	@Test
	void dbServiceTests() {
		try {
			DBServiceTest dbServiceTest = new DBServiceTest();
			dbServiceTest.dbConnectionTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To execute and test operations related to GameService
	 */
	@Test
	void gameServiceTests() {
		try {
			GameServiceTest gameServiceTest = new GameServiceTest();
			gameServiceTest.gameAddTest();
			gameServiceTest.gameUpdateTest();
			gameServiceTest.gameGetTest();
			gameServiceTest.gameRemoveTest();
			gameServiceTest.gameGetByPlayerTest();
			gameServiceTest.gameGetAllTest();
			gameServiceTest.gameGetLeaderBoardTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To execute and test operations related to IPRegistryAPIService
	 */
	@Test
	void IPRegistryAPIServiceTests() {
		try {
			IPRegistryAPIServiceTest ipRegistryAPIServiceTest = new IPRegistryAPIServiceTest();
			ipRegistryAPIServiceTest.validIPRegistryGetLocationTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To execute and test operations related to LoginService
	 */
	@Test
	void LoginServiceTests() {
		try {
			LoginServiceTest loginServiceTest = new LoginServiceTest();
			loginServiceTest.validLoginTest();
			loginServiceTest.inValidLoginTest();
			loginServiceTest.availableUsernameTest();
			loginServiceTest.unavailableUsernameTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To execute and test operations related to QuestionService
	 */
	@Test
	void QuestionServiceTests() {
		try {
			QuestionServiceTest questionServiceTest = new QuestionServiceTest();
			questionServiceTest.questionAddTest();
			questionServiceTest.questionUpdateTest();
			questionServiceTest.questionGetTest();
			questionServiceTest.questionRemoveTest();
			questionServiceTest.questionGetByCountTest();
			questionServiceTest.questionGetAllTest();
			questionServiceTest.questionCheckSolutionTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To execute and test operations related to QuizService
	 */
	@Test
	void QuizServiceTests() {
		try {
			QuizServiceTest quizServiceTest = new QuizServiceTest();
			quizServiceTest.getQuestionnaireTest();
			quizServiceTest.getQuizResultsTest1();
			quizServiceTest.getQuizResultsTest2();
			quizServiceTest.getQuizResultsTest3();
			quizServiceTest.getQuizResultsTest4();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To execute and test operations related to UserService
	 */
	@Test
	void UserServiceTests() {
		try {
			UserServiceTest userServiceTest = new UserServiceTest();
			userServiceTest.userAddTest();
			userServiceTest.userUpdateTest();
			userServiceTest.userGetTest();
			userServiceTest.userRemoveTest();
			userServiceTest.userGetAllTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
