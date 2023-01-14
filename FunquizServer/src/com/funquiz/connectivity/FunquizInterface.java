package com.funquiz.connectivity;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.funquiz.models.Game;
import com.funquiz.models.Location;
import com.funquiz.models.Question;
import com.funquiz.models.QuizReport;
import com.funquiz.models.User;

/**
 * Remote interface which specifies all methods that can be used by the RMI Client
 * 
 * @author Oshadee Amarasinghe
 *
 */
public interface FunquizInterface extends Remote {

	/**
	 * To determine the status of the database connection (connected or not)
	 * 
	 * @return Whether connection to database is established (true) or not (false)
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean getServerStatus() throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * To retrieve random quiz(questionnaire) based on a user specified size
	 * 
	 * @param questionnaireSize To obtain size of the questionnaire that needs to retrieved
	 * @return List of questions which is limited by the questionnaireSize param
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	public List<Question> getQuizQuestionnaire(int questionnaireSize) throws RemoteException, ClassNotFoundException, SQLException, MalformedURLException;

	/**
	 * To retrieve the results and total score based on the taken quiz
	 * 
	 * @param user To obtain the username of the user who has taken the quiz
	 * @param questionnaire List of questions which contains the question and the selected solution
	 * @return List of QuestionReports and the score to the specific taken quiz
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public QuizReport getQuizResults(User user, List<Question> questionnaire) throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * To retrieve the top players of each quiz category (10, 20, 30, 40, 50, 75, etc.)
	 * 
	 * @return List of Game objects representing the top payers
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public List<Game> getLeaderBoard() throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * To retrieve the previous quizes palyed by a specific user
	 * 
	 * @param user To obtain the username of the user who is requesting the previous quizs played by him/her
	 * @return List of Game objects which represents the previous quizes taken by a speicifc user
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public List<Game> getPreviousGamesByUser(User user) throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * To create a new quiz question in the database
	 * 
	 * @param question To obtain the question url to create the database entry
	 * @return Whether question creation was successful (true) or not (false)
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean addQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * To update an existing quiz question in the database
	 * 
	 * @param question To obtain the question id and url to update a specific database entry
	 * @return Whether question update was successful (true) or not (false)
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean updateQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * To remove an existing quiz question from the database
	 * 
	 * @param question To obtain the question id to remove a specific database entry
	 * @return Whether question removal was successful (true) or not (false)
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean removeQuestion(Question question) throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * To retrieve all the quiz question available in the database
	 * 
	 * @return List of Question objects which represents all the questions in the database
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public List<Question> getAllQuestion() throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * To register new user into the system as a player
	 * 
	 * @param user To obtain the username, display name and password of the user to create a database entry
	 * @return Whether user creation was successful (true) or not (false)
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean registerUser(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * To check the availability of a selected username when registering to the system
	 * 
	 * @param username To check the the availability of the selected username 
	 * @return Whether username is available (true) or not (false)
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean isUserNameAvailable(String username) throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * To validate username and password upon login
	 * 
	 * @param user To obtain the username and password to authenticate credentails
	 * @return Whether user was authencated (User) or not (null)
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public User authenticateUser(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * To update an existing user in the database 
	 * 
	 * @param user To obtain the username, display name and password of the user to update a specific database entry
	 * @return Whether user update was successful (true) or not (false)
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean updateUser(User user) throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * To remove a existing user from the database
	 * 
	 * @param user To obtain  the username of the user about to be removed
	 * @return Whether user removal was successful (true) or not (false)
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean removeUser(User user) throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * To retrieve location details of the logging in user using IP
	 * 
	 * @param ip IP address of the user who is logging in
	 * @return Location object containing the IP, City, Postal Code, Latitude, Longitude and ISPName of the logging in user 
	 * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur during the execution of a remote method call
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 */
	public Location getLocationByIP(String ip) throws RemoteException, ClassNotFoundException;
}
