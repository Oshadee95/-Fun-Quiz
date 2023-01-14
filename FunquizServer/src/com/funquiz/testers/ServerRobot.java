package com.funquiz.testers;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

/**
 * AWT Robot to test RMI server app.
 *
 * @author Oshadee Amarasinghe
 */
public class ServerRobot {

	/**
	 * Instance of the Robot Class
	 */
	Robot robot;
	
	/**
	 * Instance of the RobotUtil Class
	 */
	RobotUtil robotUtil;

	/**
	 * Executes the RobotTester
	 * 
	 * @param args Default parameter for main method
	 */
	public static void main(String[] args) {
		try {
			
			new ServerRobot();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executes the testing of the RMI server application
	 * 
	 * @throws AWTException         A generic exception that can be thrown when an exceptional condition has occurred within AWT
	 * @throws IOException          Exception which programmers use in the code to throw a failure in Input and Output operations
	 * @throws InterruptedException Thrown when a thread is interrupted while it's waiting, sleeping, or otherwise occupied
	 */
	public ServerRobot() throws AWTException, IOException, InterruptedException {
		/**
		 * https://stackoverflow.com/questions/24653790/how-to-run-exe-file-in-java-program
		 */
		String command = "cmd /c start cmd.exe /K \"FunquizServerApp.jar";
		Runtime run = Runtime.getRuntime();

		run.exec(command);
		Thread.sleep(3000);
		
		/**
		 * https://stackoverflow.com/questions/17693266/how-to-close-command-prompt-using-java-if-already-opened
		 * Exiting the cmd 
		 */
		run.exec("taskkill /f /im cmd.exe") ;

		robot = new Robot();
		robot.setAutoDelay(40);
		robot.setAutoWaitForIdle(true);
		
		robotUtil = new RobotUtil(robot);
		
		robotUtil.movePointer(1143, 606);

		// Starting RMI Server
		robotUtil.leftClick();
		robotUtil.delayExecution(1500);

		// Stopping RMI Server
		robotUtil.leftClick();
		robotUtil.delayExecution(1500);

		// Exiting application
		robotUtil.movePointer(1180, 413);
		robotUtil.leftClick();
	}
}
