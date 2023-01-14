package com.funquiz.connectivity;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.funquiz.admin.peripherals.AdminGUI;
import com.funquiz.common.peripherals.ErrorNotifier;
import com.funquiz.login.peripherals.LoginGUI;
import com.funquiz.models.Location;
import com.funquiz.models.User;
import com.funquiz.player.peripherals.PlayerGUI;
import com.funquiz.services.IpifyAPIService;

/**
 * RMI Client to establish connection to the RMI Server
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class FunquizClient {

	// Declaring funquizInterface, this instance is used access (add, retrieve, update, remove from the database) the remote connection
	public static FunquizInterface funquizInterface;

	// Declaring LoginPanel, PlayerPanel and AdminPanel to be used any where in the application either to load or dispose relevant JFrame without 
	// creating multiple JFrames for the same purpose
	public static AdminGUI adminGUI;
	public static PlayerGUI playerGUI;
	public static LoginGUI loginGUI;

	// Declaring authUser to act as an cookie, the cookie stores the information of the currently signed-in lecturer/student, 
	// and can be accessed from any where within the application
	public static User authUser;

	// Declaring authLocation used to store the signed-in Players's/Admin's IP  address, and can be accessed from any where within the application incase if needed
	public static Location authLocation;

	public static void main(String args[]) {
		try {
			/**
			 * Error occurred while trying to start the client server. Error -> No security
			 * manager Solution -> Both client and server packages which uses RMI server
			 * should be named the same.
			 * 
			 * @link https://stackoverflow.com/questions/6322107/java-no-security-manager-rmi-class-loader-disabled.Answered by erickson, 
			 * edited by Paulo Ebermann
			 */

			// Creating a registry object with a specific port to be looked-up
			Registry registry = LocateRegistry.getRegistry(1417);
			System.out.println("Registry located");

			// Checking and establishing remote connection if the below url can be accessed
			// using the pre-defined port
			funquizInterface = (FunquizInterface) registry.lookup("rmi://localhost/Funquiz");
			System.out.println("Server located");

			// Retreieves user's IP address using Ipify API
			String ip = IpifyAPIService.getIP();
			System.out.println("User located at " + ip + " using Ipify Web API");

			System.out.println("Retreiveing information from IP using IP-Registry Web API");
			authLocation = funquizInterface.getLocationByIP(ip);
			authLocation.displayDetails();

			// Checks to determine if connection to database can be established through the remote connection
			if (funquizInterface.getServerStatus()) {
				System.out.println("Connection to database established");

				// Opening up loginPanel if not exception or error was thrown
				loginGUI = new LoginGUI();
				loginGUI.setVisible(true);

			} else {
				new ErrorNotifier("Failed to establish connection to the database !\\nPlease contact the administrator").setVisible(true);
			}

		} catch (Exception e) {
			new ErrorNotifier(e.getCause().getMessage()).setVisible(true);
			e.printStackTrace();
		}
	}
}
