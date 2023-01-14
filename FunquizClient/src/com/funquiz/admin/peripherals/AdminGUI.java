package com.funquiz.admin.peripherals;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.funquiz.common.peripherals.AvatarPanel;
import com.funquiz.common.peripherals.ContentPanel;
import com.funquiz.common.peripherals.NamePanel;
import com.funquiz.common.peripherals.NavigationPanel;
import com.funquiz.common.peripherals.UI;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public class AdminGUI extends JFrame {

	/*
	 * Declaring the NavigationPanels on left side of the application which is used
	 * to navigate to different content panels To identify the active navigation,
	 * NavigationPanel type arraylist(navigationPanelList) is implemented
	 */
	private NavigationPanel quizNavigationPanel = new QuizNavigationGUI();
	private NavigationPanel logoutNavigationPanel = new LogoutNavigationPanel();
	private static ArrayList<NavigationPanel> navigationPanelList;
	public static NavigationPanel selectedNavigation;

	/*
	 * Declaring the ContentPanels on right side of the application which consists
	 * of dynamic data retrieved from the databse To identify the active content
	 * panel, ContentPanel type arraylist(contentPanelList) is implemented
	 */
	public static ContentPanel selectedContent;

	// Avatar and auth user name components
	private AvatarPanel avatar = new AvatarPanel();
	private NamePanel authUserName = new NamePanel();
	private static JPanel rightSidePanel;

	/*
	 * LecturerPanel method : used to initialize JFrame, UI elements to style and
	 * support functionality of JFrame implemented here
	 */
	public AdminGUI() {

		// Setting LecturerPanel JFrame's icon
//		setIconImage(new ImageIcon(getClass().getResource("/resources/logo-2.png")).getImage());

		// Setting JFrame title text
		setTitle("FUNQUIZ | ADMIN");

		// Setting the size of the application screen
		setSize(UI.APPLICATION_PRIMARY_FRAME_WIDTH, UI.APPLICATION_PRIMARY_FRAME_HEIGHT);
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
		leftSidePanel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		leftSidePanel.setBounds(UI.NAVIGATION_PANEL_X_AXIS, UI.NAVIGATION_PANEL_Y_AXIS, UI.NAVIGATION_PANEL_WIDTH,
				UI.NAVIGATION_PANEL_HEIGHT);
		getContentPane().add(leftSidePanel);
		leftSidePanel.setLayout(null);

		// Adding avatar and auth user's name to left-side JPanel
		leftSidePanel.add(avatar.getAvatar());
		leftSidePanel.add(authUserName.getName());

		/*
		 * Adding the navigation panels to an ArrayList. setSelectedNavigationPanel
		 * method has the implementation to loop through the arraylist to set color to
		 * selected-navigation panel and set relevant content panel accordingly
		 */
		navigationPanelList = new ArrayList<NavigationPanel>();
		navigationPanelList.add(quizNavigationPanel);
		navigationPanelList.add(logoutNavigationPanel);

		// Adding navigation JPanels to left-side JPanel
		leftSidePanel.add(quizNavigationPanel.getNavigation());
		leftSidePanel.add(logoutNavigationPanel.getNavigation());

		// Adding left-side JPanel which is on the right side of the application. Used
		// as the application's content panel
		rightSidePanel = new JPanel();
		rightSidePanel.setBounds(UI.NAVIGATION_PANEL_WIDTH, 0, UI.CONTENT_PANEL_WIDTH, UI.CONTENT_PANEL_HEIGHT);
		getContentPane().add(rightSidePanel);
		rightSidePanel.setLayout(null);

		// Setting dashboard as root component on both navigation and content on
		// application startup
		AdminGUI.selectedNavigation = new QuizNavigationGUI();
		AdminGUI.selectedContent = new QuizContentGUI();
		setSelectedPanel();
	}

	/*
	 * setSelectedPanel method : used to call setSelectedNavigationPanel and
	 * setSelectedContentPanel methods to keep implemention simple and
	 * abstract(reduce complexity)
	 */
	public static void setSelectedPanel() {
		// Calling setSelectedNavigationPanel method to set color to selected
		// NavigationPanel
		setSelectedNavigationPanel();

		// Calling setSelectedContentPanel method to set relevant ContentPanel according
		// to the NavigationPanel selected
		setSelectedContentPanel();
	}

	/*
	 * setSelectedNavigationPanel method : used to set the selected navigation
	 * panel, set one color to the selected navigation panel and repaint rest with
	 * another color This method is implemented to enhance UX
	 */
	private static void setSelectedNavigationPanel() {
		// Executing a foreach loop to set the color of the selected navigation panel,
		// if block of the selected navigation and else block for the rest
		for (NavigationPanel NavigationPanel : navigationPanelList) {
			/*
			 * Selected navigation is cross-checked against the existing static nagivation
			 * panels, inorder to avoid creation of multiple navigationPanels
			 * NavigationPanel's (JPanel) name is used as a unique key to identify the
			 * navigationPanels
			 */
			if (NavigationPanel.getNavigation().getName().toString()
					.equalsIgnoreCase(AdminGUI.selectedNavigation.getNavigation().getName().toString())) {
				NavigationPanel.getNavigation().setBackground(UI.NAVIGATION_PANEL_SELECTED_BUTTON_COLOR);
			} else {
				NavigationPanel.getNavigation().setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			}
		}
	}

	/*
	 * setSelectedContentPanel method : used to remove the contentPanel in currently
	 * use and add the ContentPanel which is relevant to the selected navigation
	 * panel
	 */
	private static void setSelectedContentPanel() {
		// Setting the relevant content panel according to the selected navigation panel
		rightSidePanel.removeAll();
		rightSidePanel.add(AdminGUI.selectedContent.getContent());
		rightSidePanel.repaint();
	}
}
