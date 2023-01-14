package com.funquiz.login.peripherals;

import javax.swing.*;

import com.funquiz.common.peripherals.ContentPanel;
import com.funquiz.common.peripherals.UI;
import com.funquiz.connectivity.FunquizClient;
import com.funquiz.models.User;
import java.awt.Color;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public class LoginGUI extends JFrame {

	/*
	 * Declaring the ContentPanels on right side of the application which consists
	 * of dynamic data retrieved from the databse To identify the active content
	 * panel, ContentPanel type arraylist(contentPanelList) is implemented
	 */
	public static ContentPanel selectedContent;

	// Avatar and auth user name components
	private static JPanel rightSidePanel;

	/*
	 * LecturerPanel method : used to initialize JFrame, UI elements to style and
	 * support functionality of JFrame implemented here
	 */
	public LoginGUI() {

		// Setting LecturerPanel JFrame's icon
		// setIconImage(new
		// ImageIcon(getClass().getResource("/resources/logo-2.png")).getImage());

		// Setting JFrame title text
		setTitle("FUNQUIZ | LOGIN");

		// Setting the size of the application screen
		setSize(UI.LOGIN_FRAME_WIDTH, UI.LOGIN_FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// setLocationRelativeTo set to null inorder to start the application center of
		// the screen
		setLocationRelativeTo(null);

		// Disabling frame resizing
		setResizable(false);

		// Adding left-side JPanel which is on the left side of the application. Used as
		// the application's navigation panel
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setBackground(Color.decode("#3B53D0"));
		leftSidePanel.setBounds(0, 0, 708, 672);
		getContentPane().add(leftSidePanel);
		leftSidePanel.setLayout(null);

		JLabel loginBackground = new JLabel("");
		loginBackground.setIcon(new ImageIcon(LoginGUI.class.getResource("/com/funquiz/resources/login-background.png")));
		loginBackground.setHorizontalAlignment(SwingConstants.CENTER);
		loginBackground.setBounds(113, 114, 428, 359);
		leftSidePanel.add(loginBackground);

		// Adding left-side JPanel which is on the right side of the application. Used
		// as the application's content panel
		rightSidePanel = new JPanel();
		rightSidePanel.setBounds(708, 0, 383, 672);
		getContentPane().add(rightSidePanel);
		rightSidePanel.setLayout(null);

		FunquizClient.authUser = new User("Uditha", "");
		// Setting dashboard as root component on both navigation and content on
		// application startup
		LoginGUI.selectedContent = new LoginContentGUI();
		setSelectedPanel();
	}

	/*
	 * setSelectedPanel method : used to call setSelectedNavigationPanel and
	 * setSelectedContentPanel methods to keep implemention simple and
	 * abstract(reduce complexity)
	 */
	public static void setSelectedPanel() {
		// Calling setSelectedContentPanel method to set relevant ContentPanel according
		// to the NavigationPanel selected
		setSelectedContentPanel();
	}

	/*
	 * setSelectedContentPanel method : used to remove the contentPanel in currently
	 * use and add the ContentPanel which is relevant to the selected navigation
	 * panel
	 */
	private static void setSelectedContentPanel() {
		// Setting the relevant content panel according to the selected navigation panel
		rightSidePanel.removeAll();
		rightSidePanel.add(LoginGUI.selectedContent.getContent());
		rightSidePanel.repaint();
	}
}
