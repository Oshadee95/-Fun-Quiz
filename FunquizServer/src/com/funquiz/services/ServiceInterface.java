package com.funquiz.services;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface to be used by service classes to maintain order and follow the best practices on object oriented concepts
 * 
 * @author Oshadee Amarasinghe
 *
 * @param <T> Type to bind the implementation class to a specific type
 */
public interface ServiceInterface<T> {

	/**
	 * To add a certain record to the database related to a specific table/view
	 * 
	 * @param type Object that contains the data to add tha paricular record
	 * @return Whether record creation has successful (true) or not (false)
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean add(T type) throws ClassNotFoundException, SQLException;

	/**
	 * To update a specific record from the database related to a specific table/view
	 * 
	 * @param type Object that contains the data to update tha paricular record
	 * @return Whether record update has successful (true) or not (false)
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean update(T type) throws ClassNotFoundException, SQLException;

	/**
	 * To delete a specific record from the database related to a specific table/view
	 * 
	 * @param type Object that contains the data to remove tha paricular record
	 * @return Whether record delete was successful (true) or not (false)
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean remove(T type) throws ClassNotFoundException, SQLException;

	/**
	 * To get a specific record from the database related to a specific table/view
	 * 
	 * @param type Object that contains the data to get tha paricular record
	 * @return Paticular record from the database
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public T get(T type) throws ClassNotFoundException, SQLException;

	/**
	 * To get get all available records from the database related to a specific table/view
	 * 
	 * @return List of objects containing the records from the database
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public List<T> getAll() throws ClassNotFoundException, SQLException;
}
