package com.funquiz.connectivity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import javax.swing.WindowConstants;

import com.funquiz.peripherals.ServerGUI;

/**
 * RMI server implementation to start and stop the server
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class FunquizServer {

	/**
	 * GUI of the server implementation 
	 */
	private static ServerGUI serverGUI;
	
	/**
	 * Registry of whcih the RMI server is instantiated
	 */
	private static Registry registry;

	/**
	 * Main entry point into the display the ServerGUI
	 * @param args Default execution parameter
	 */
	public static void main(String[] args) {
		serverGUI = new ServerGUI();
		serverGUI.setVisible(true);

		serverGUI.getServerButton().addMouseListener(new MouseAdapter() {
			/**
			 * To either start or stop RMI server based on mouse click events
			 * @param mouseEvent To get information about the mouse click
			 */
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				// Checking if the server is already running
				if (serverGUI.getServerButtonLabel().equals("Start Server")) {
					try {
						// Starting server
						startServer();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						// Stopping server if already running
						stopServer();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		serverGUI.addWindowListener(new java.awt.event.WindowAdapter() {
			/**
			 * To stop the RMI server before JFrame's windowClosing event
			 * @param windowEvent To get information about the windowClosing event
			 */
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					// Stopping RMI server if the server has already started
					if(serverGUI.getServerButtonLabel().equals("Stop Server")) {
						stopServer();
					}
					// Closing JFrame after the RMI server is stopped
					serverGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * To start RMI server
	 * 
	 * @throws RemoteException If an attempt to export the remote object fails
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 * @throws AlreadyBoundException If an attempt is made to bind an object in the registry to a name that already has an associated binding
	 */
	private static void startServer() throws RemoteException, ClassNotFoundException, SQLException, AlreadyBoundException {
		serverGUI.setServerStatus("Creating local registry ...");

		// Opening a new port in rmi registery
		registry = LocateRegistry.createRegistry(1417);
		LocateRegistry.getRegistry();
		serverGUI.setServerStatus("Registry Created");

		// The stub is an object, acts as a gateway for the client side. All the outgoing requests are routed through it
		Funquiz stub = new Funquiz();
		serverGUI.setServerStatus("Connecting to database ...");

		// Checking if connection to database can be established
		if (stub.getServerStatus()) {
			serverGUI.setServerStatus("Connected to the database");
			serverGUI.setServerStatus("Starting RMI Server ...");

			// Creating a proxy for data communication
			// Binding the port with the stub
			registry.bind("rmi://localhost/Funquiz", stub);
			serverGUI.setServerStatus("RMI Server is ready");

			// Setting server button to stop server since at this point the server has started 
			serverGUI.setButtonText("Stop Server");

		} else {
			System.out.println("Failed to connect to on database");
		}
	}

	/**
	 * To stop RMI server
	 * 
	 * @throws AccessException If accessor has an unexpected problem
	 * @throws RemoteException If an attempt to export the remote object fails
	 * @throws NotBoundException if an attempt is made to lookup or unbind in the registry a name that has no associated binding
	 */
	private static void stopServer() throws AccessException, RemoteException, NotBoundException {
		// Checking if registry has been created
		if (registry != null) {
			// Removing stub from the registered registry
			UnicastRemoteObject.unexportObject(registry, true);
			
			// Setting default text to server status log
			serverGUI.clearServerStatus();
			
			// Setting server button to start server
			serverGUI.setButtonText("Start Server");
		}
	}
}
