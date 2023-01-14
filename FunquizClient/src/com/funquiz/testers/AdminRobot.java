package com.funquiz.testers;

import java.awt.Robot;

public class AdminRobot extends FunquizRobot {

	public AdminRobot(Robot robot) {
		super(robot);
	}
	
	@Override
	public void logout() {
		this.robot.delayExecution(1500);
		this.robot.movePointer(471, 569);
		this.robot.leftClick();
	}
}
