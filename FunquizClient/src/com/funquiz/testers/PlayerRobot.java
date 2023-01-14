package com.funquiz.testers;

import java.awt.Robot;

public class PlayerRobot extends FunquizRobot {

	private int selectedQuizSize;

	public PlayerRobot(Robot robot) {
		super(robot);
	}

	@Override
	public void logout() {
		this.robot.delayExecution(1500);
		this.robot.movePointer(489, 630);
		this.robot.leftClick();
	}

	public void register(String displayName, String username, String password) {
		this.robot.movePointer(1424, 729);

		// Navigating to register new player
		this.robot.leftClick();
		this.robot.delayExecution(1500);

		// CLicking on display name text box
		this.robot.movePointer(1196, 436);
		this.robot.leftClick();

		// Typing display name
		this.robot.type(displayName);
		this.robot.pressTab();

		// Typing username
		this.robot.type(username);
		this.robot.pressTab();

		// Typing password
		this.robot.type(password);
		this.robot.delayExecution(1500);

		// Clicking signup button
		this.robot.movePointer(1412, 722);
		this.robot.leftClick();
	}

	public void switchToQuizTab() {
		this.robot.delayExecution(1500);
		this.robot.movePointer(472, 498);
		this.robot.leftClick();
	}

	public void switchToResultsTab() {
		this.robot.delayExecution(1500);
		this.robot.movePointer(472, 569);
		this.robot.leftClick();
	}

	public void selectQuizSize(int quizSize) {
		this.robot.delayExecution(500);
		this.selectedQuizSize = quizSize;
		switch (quizSize) {
		case 10:
			this.robot.movePointer(814, 470);
			break;
		case 20:
			this.robot.movePointer(915, 470);
			break;
		case 30:
			this.robot.movePointer(1016, 470);
			break;
		case 40:
			this.robot.movePointer(1113, 470);
			break;
		case 50:
			this.robot.movePointer(1217, 470);
			break;
		case 75:
			this.robot.movePointer(1323, 470);
			break;
		case 100:
			this.robot.movePointer(808, 545);
			break;
		case 200:
			this.robot.movePointer(923, 545);
			break;
		case 300:
			this.robot.movePointer(1011, 545);
			break;
		case 400:
			this.robot.movePointer(1112, 545);
			break;
		case 500:
			this.robot.movePointer(1221, 545);
			break;
		case 600:
			this.robot.movePointer(1318, 545);
			break;
		case 700:
			this.robot.movePointer(917, 626);
			break;
		case 800:
			this.robot.movePointer(1016, 626);
			break;
		case 900:
			this.robot.movePointer(1112, 626);
			break;
		case 1000:
			this.robot.movePointer(1212, 626);
			break;
		default:
			this.robot.movePointer(814, 470);
		}

		this.robot.leftClick();
	}

	public void clickStartQuiz() {
		this.robot.delayExecution(1500);
		this.robot.movePointer(1069, 751);
		this.robot.leftClick();
	}

	public void selectRandonAnswers() {
		this.robot.delayExecution(1000);
		this.robot.movePointer(1389, 408);
		this.robot.leftClick();
		this.robot.leftClick();

		for (int i = 1; i < selectedQuizSize; i++) {
			this.robot.delayExecution(1500);
			switch (getRandomNumberInRange(-1, 9)) {
			case -1:this.robot.movePointer(1445, 808);
				break;
			case 0: this.robot.movePointer(1389, 408);
				break;
			case 1: this.robot.movePointer(1491, 408);
				break;
			case 2:this.robot.movePointer(1391, 479);
				break;
			case 3:this.robot.movePointer(1501, 479);
				break;
			case 4:this.robot.movePointer(1388, 560);
				break;
			case 5:this.robot.movePointer(1491, 560);
				break;
			case 6:this.robot.movePointer(1390, 641);
				break;
			case 7:this.robot.movePointer(1493, 641);
				break;
			case 8:this.robot.movePointer(1396, 716);
				break;
			case 9:this.robot.movePointer(1486, 716);
				break;
			}
			this.robot.leftClick();
		}
	}

	public static void main(String args[]) {

		for (int i = 0; i < 10; i++) {
			System.out.println(getRandomNumberInRange(-1, 9));
		}
	}

	private static int getRandomNumberInRange(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}
	
	public void clickRetakeQuiz() {
		this.robot.delayExecution(1500);
		this.robot.movePointer(971, 603);
		this.robot.leftClick();
	}
	
	public void clickPrintResults() {
		this.robot.delayExecution(1500);
		this.robot.movePointer(1193, 600);
		this.robot.leftClick();
	}
	
	public void clickLeaderboard() {
		this.robot.delayExecution(1500);
		this.robot.movePointer(1423, 305);
		this.robot.leftClick();
	}
	
	public void clickPrintPreviousResults() {
		this.robot.delayExecution(1500);
		this.robot.movePointer(1436, 389);
		this.robot.leftClick();
		this.robot.leftClick();
	}

}
