package com.funquiz.testers;

import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * Common utilites class to handle basic robot operations
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class RobotUtil {
	
	/**
	 * Instance of the Robot Class
	 */
	Robot robot;
	
	/**
	 * Initializes the robot object 
	 * 
	 * @param robot Instance of the parent Robot object
	 */
	public RobotUtil(Robot robot) {
		this.robot = robot;
	}
	
	/**
	 * Delays the execution of the next execution
	 * 
	 * @param noOfSeconds Number of seconds to delay the execution of the next
	 *                    process by
	 */
	public void delayExecution(int noOfSeconds) {
		robot.delay(noOfSeconds);
	}

	/**
	 * Moves the move cursor to a specific coordinate on the screen
	 * 
	 * @param xCoordinate Horizintal coordinate on the screen
	 * @param yCoordinate Vertical coordinate on the screen
	 */
	public void movePointer(int xCoordinate, int yCoordinate) {
		robot.mouseMove(xCoordinate, yCoordinate);
	}

	/**
	 * Executes the mouse left click
	 */
	public void leftClick() {
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
}
