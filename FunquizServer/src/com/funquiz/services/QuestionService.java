package com.funquiz.services;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.funquiz.models.Question;
import com.funquiz.utils.URLUtil;

/**
 * QuestionService to execute operations related to the questions table in the database 
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class QuestionService implements ServiceInterface<Question> {

	@Override
	public boolean add(Question question) throws ClassNotFoundException, SQLException {
		int execution = 0;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "INSERT INTO `questions`(`imageId`) VALUES (?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, URLUtil.getDestructuredQuestionURL(question.getUrl()));

			execution = ps.executeUpdate();
		}
		return execution != 0;
	}

	@Override
	public boolean update(Question question) throws ClassNotFoundException, SQLException {
		int execution = 0;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "UPDATE `questions` SET `imageId` = ? WHERE qid = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, URLUtil.getDestructuredQuestionURL(question.getUrl()));
			ps.setInt(2, question.getId());
			execution = ps.executeUpdate();
		}
		return execution != 0;
	}

	@Override
	public boolean remove(Question question) throws ClassNotFoundException, SQLException {
		int execution = 0;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "DELETE FROM `questions` WHERE qid = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, question.getId());

			execution = ps.executeUpdate();
		}
		return execution != 0;
	}

	@Override
	public Question get(Question question) throws ClassNotFoundException, SQLException {
		Question dbQuestion = null;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "SELECT * from `questions` WHERE `qid` = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, question.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				try {
					dbQuestion = new Question(rs.getInt(1), URLUtil.getStructuredQuestionURL(rs.getString(2)), getSolution(rs.getString(2)));
				} catch (Exception e) {
					dbQuestion = null;
					e.printStackTrace();
				}
			}
		}
		return dbQuestion;
	}

	@Override
	public List<Question> getAll() throws ClassNotFoundException, SQLException {
		List<Question> questionList = null;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "SELECT * from `questions`";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			questionList = new ArrayList<Question>();
			while (rs.next()) {
				try {
					questionList.add(new Question(rs.getInt(1), URLUtil.getStructuredQuestionURL(rs.getString(2)), getSolution(rs.getString(2))));
				} catch (Exception e) {
					questionList = null;
					e.printStackTrace();
				}
			}
		}
		return questionList;
	}

	
	/**
	 * To retrieve random quiz(questionnaire) based on a user specified size
	 * 
	 * @param questionnaireSize To obtain size of the questionnaire that needs to retrieved
	 * @return List of questions which is limited by the questionnaireSize param
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	public List<Question> getByCount(int questionnaireSize) throws ClassNotFoundException, SQLException, MalformedURLException {
		List<Question> questionnaire = null;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "SELECT `imageId` FROM `questions` ORDER BY RAND() LIMIT " + questionnaireSize;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			questionnaire = new ArrayList<Question>();
			while (rs.next()) {
				questionnaire.add(new Question(URLUtil.getStructuredQuestionURL(rs.getString(1))));
			}
		}
		return questionnaire;
	}
	
	/**
	 * To retrieve the correct solution for the provided question url
	 * 
	 * @param url Indicate the question url of which to obtain the solution from
	 * @return Correct solution for the provided question url
	 */
	public int getSolution(String url) {
		String questionId = url.replaceAll("[^\\d]", "");
		return Integer.parseInt(String.valueOf(questionId.charAt(questionId.length() - 1)));
	}
}
