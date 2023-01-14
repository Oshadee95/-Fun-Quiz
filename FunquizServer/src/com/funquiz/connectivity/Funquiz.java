package com.funquiz.connectivity;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import com.funquiz.models.Game;
import com.funquiz.models.Location;
import com.funquiz.models.Question;
import com.funquiz.models.QuizReport;
import com.funquiz.models.User;
import com.funquiz.services.DBService;
import com.funquiz.services.GameService;
import com.funquiz.services.IPRegistryAPIService;
import com.funquiz.services.LoginService;
import com.funquiz.services.QuestionService;
import com.funquiz.services.QuizService;
import com.funquiz.services.UserService;

/**
 * Implementation of the RMI remote interface 
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public class Funquiz extends UnicastRemoteObject implements FunquizInterface {

	/**
	 * Initializes Funquiz class and defines class constructor as private
	 * 
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 */
	protected Funquiz() throws RemoteException {
		super();
	}

	@Override
	public boolean getServerStatus() throws RemoteException, ClassNotFoundException, SQLException {
		return DBService.getDBConnection() != null;
	}

	@Override
	public List<Question> getQuizQuestionnaire(int questionnaireSize) throws RemoteException, ClassNotFoundException, SQLException, MalformedURLException {
		return new QuizService().getQuestionnaire(questionnaireSize);
	}
	
	@Override
	public List<Game> getLeaderBoard() throws RemoteException, ClassNotFoundException, SQLException {
		return new GameService().getLeaderBoard();
	}
	
	@Override
	public List<Game> getPreviousGamesByUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		return new GameService().getPlayerGames(user);
	}

	@Override
	public QuizReport getQuizResults(User user, List<Question> questionnaire) throws RemoteException, ClassNotFoundException, SQLException {
		return new QuizService().getQuizResults(user, questionnaire);
	}

	@Override
	public boolean addQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		return new QuestionService().add(question);
	}

	@Override
	public boolean updateQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		return new QuestionService().update(question);
	}

	@Override
	public boolean removeQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException {
		return new QuestionService().remove(question);
	}
	
	@Override
	public List<Question> getAllQuestion() throws RemoteException, ClassNotFoundException, SQLException {
		return new QuestionService().getAll();
	}

	@Override
	public boolean registerUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		return new UserService().add(user);
	}
	
	
	@Override
	public boolean isUserNameAvailable(String username) throws RemoteException, ClassNotFoundException, SQLException {
		return new LoginService().isUserNameAvailable(username);
	}
	
	@Override
	public User authenticateUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		return new LoginService().login(user);
	}

	@Override
	public boolean updateUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		return new UserService().update(user);
	}

	@Override
	public boolean removeUser(User user) throws RemoteException, ClassNotFoundException, SQLException {
		return new UserService().remove(user);
	}

	@Override
	public Location getLocationByIP(String ip)throws RemoteException, ClassNotFoundException {
		return new IPRegistryAPIService().getLocation(ip);
	}
}
