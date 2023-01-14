package com.funquiz.testers;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RobotUtil {
	
	/**
	 * Instance of the Robot Class
	 */
	Robot robot;
	
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

	/**
	 * 
	 */
	public void pressEnter() {
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	public void pressTab() {
		robot.keyPress(KeyEvent.VK_TAB);
	}

	public void type(int keyCodes) {
		robot.delay(40);
		robot.keyPress(keyCodes);
		robot.keyRelease(keyCodes);
	}

	public void type(String word) {
		byte[] bytes = word.getBytes();
		for (byte b : bytes) {
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123) {
				code = code - 32;
			}
			robot.delay(40);
			robot.keyPress(code);
			robot.keyRelease(code);
		}
	}

	public void type(int... keyCodes) {
		if (keyCodes.length == 0) {
			return;
		}
		robot.delay(40);
		for (int i = 0; i < keyCodes.length; i++) {
			robot.keyPress(keyCodes[i]);
		}
		
		for (int i = 0; i < keyCodes.length; i++) {
			robot.keyRelease(keyCodes[i]);
		}
	}
}
