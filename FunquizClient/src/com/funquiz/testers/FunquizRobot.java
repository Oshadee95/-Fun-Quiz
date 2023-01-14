package com.funquiz.testers;

import java.awt.Robot;

abstract class FunquizRobot {
	
	/**
	 * Instance of the RobotUtil Class
	 */
	RobotUtil robot;

	public FunquizRobot(Robot robot) {
		this.robot = new RobotUtil(robot);
	}
	
	public void login(String username, String password) {
		// Typing username
		robot.type(username);
		robot.pressTab();

		// Typing password
		robot.type(password);

		// Pressing enter to login
		robot.pressEnter();
	}
	
	public abstract void logout();
	
	public void clickOk() {
		this.robot.delayExecution(4000);
		this.robot.movePointer(1131, 579);
		this.robot.leftClick();
	}
	
	public void exit() {
		this.robot.delayExecution(500);
		this.robot.movePointer(1471, 199);
		this.robot.leftClick();
	}
}
