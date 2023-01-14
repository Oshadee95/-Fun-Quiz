package com.funquiz.testers;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

/**
 * AWT Robot to test RMI server app.
 *
 * @author Oshadee Amarasinghe
 */
public class ClientRobot {

	/**
	 * Instance of the Robot Class
	 */
	Robot clientRobot, serverRobot;

	RobotUtil robotUtil;

	/**
	 * Executes the testing of the RMI server application
	 * 
	 * @throws AWTException         A generic exception that can be thrown when an exceptional condition has occurred within AWT
	 * @throws IOException          Exception which programmers use in the code to throw a failure in Input and Output operations
	 * @throws InterruptedException Thrown when a thread is interrupted while it's waiting, sleeping, or otherwise occupied
	 */
	public void executeClientRobot() throws AWTException, IOException, InterruptedException {
		/**
		 * https://stackoverflow.com/questions/24653790/how-to-run-exe-file-in-java-program
		 */
		String command = "cmd /c start cmd.exe /K \"funquizClientApp.jar";
		Runtime run = Runtime.getRuntime();

		run.exec(command);
		Thread.sleep(4500);

		/**
		 * https://stackoverflow.com/questions/17693266/how-to-close-command-prompt-using-java-if-already-opened
		 * Exiting the cmd
		 */
		run.exec("taskkill /f /im cmd.exe");

		clientRobot = new Robot();
		clientRobot.setAutoDelay(50);
		clientRobot.setAutoWaitForIdle(true);

		// Admin robot tests
		AdminRobot adminRobot = new AdminRobot(clientRobot);
		adminRobot.login("admin", "123456");
		adminRobot.logout();

		// Player robot tests
		PlayerRobot playerRobot = new PlayerRobot(clientRobot);
		playerRobot.register("oshadee amarasinghe", "oshadee3", "123456");
		playerRobot.logout();
		playerRobot.login("oshadee3", "123456");
		playerRobot.switchToQuizTab();
		playerRobot.selectQuizSize(10);
		playerRobot.clickStartQuiz();
		playerRobot.selectRandonAnswers();
		playerRobot.clickPrintResults();
		playerRobot.clickOk();
		playerRobot.switchToResultsTab();
		playerRobot.clickPrintPreviousResults();
		playerRobot.clickOk();
		playerRobot.clickLeaderboard();
		playerRobot.logout();
		playerRobot.exit();
	}

	public void startServerRobot() throws IOException, InterruptedException, AWTException {
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
		run.exec("taskkill /f /im cmd.exe");

		serverRobot = new Robot();
		serverRobot.setAutoDelay(40);
		serverRobot.setAutoWaitForIdle(true);

		robotUtil = new RobotUtil(serverRobot);

		robotUtil.movePointer(1143, 606);

		// Starting RMI Server
		robotUtil.leftClick();
		robotUtil.delayExecution(1500);
	}

	public void exitServerRobot() {
		robotUtil.delayExecution(1000);
		robotUtil.movePointer(1143, 606);

		// Stopping RMI Server
		robotUtil.leftClick();
		robotUtil.delayExecution(1500);

		// Exiting application
		robotUtil.movePointer(1180, 413);
		robotUtil.leftClick();
	}

	/**
	 * Executes the RobotTester
	 * 
	 * @param args Default parameter for main method
	 */
	public static void main(String[] args) {
		try {

			ClientRobot clientRobot = new ClientRobot();
			clientRobot.startServerRobot();
			clientRobot.executeClientRobot();
			clientRobot.exitServerRobot();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
