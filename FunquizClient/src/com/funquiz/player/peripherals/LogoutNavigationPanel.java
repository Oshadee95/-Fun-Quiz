package com.funquiz.player.peripherals;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.funquiz.common.peripherals.NavigationPanel;
import com.funquiz.common.peripherals.UI;
import com.funquiz.connectivity.FunquizClient;
import com.funquiz.login.peripherals.LoginGUI;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public class LogoutNavigationPanel extends NavigationPanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain the sub
	// UI elements and their properties and styling
	private JPanel panel = new JPanel();

	/*
	 * DashboardNavigationPanel method : used to initialize JPanel, required
	 * properties and add UI elements to the JPanel
	 */
	public LogoutNavigationPanel() {
		/*
		 * Adding navigation button to NavigationPanel JPanel name is set to identify
		 * navigation panel when selected
		 */
		panel.setName("logout");
		panel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		panel.setBounds(0, 395, UI.NAVIGATION_PANEL_WIDTH, UI.NAVIGATION_PANEL_BUTTON_HEIGHT);
		panel.setLayout(null);
		panel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));

		panel.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseClicked to handle mouse click events Lecturer will be navigated
			 * to DashboardContentPanel on mouse click
			 * 
			 * @param arg0 to get information about the mosue click
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Each time when a mouse click event is triggerd, current NavigationPanel will
				 * be set as selectedNavigation and current ContentPanel will be set as
				 * selectedContent setSelectedPanel() will be executed to update the already
				 * selected NavigationPanel with current selected NavigationPanel along with
				 * relevant ContentPanel
				 */
				PlayerGUI.selectedNavigation = new LogoutNavigationPanel();
				PlayerGUI.selectedContent = new QuizContentGUI();
				PlayerGUI.setSelectedPanel();

				FunquizClient.loginGUI = new LoginGUI();
				FunquizClient.loginGUI.setVisible(true);
				FunquizClient.authUser = null;
				FunquizClient.playerGUI.dispose();
			}
		});

		// Adding navigation button(JPanel) text to panel
		JLabel navigationLabel = new JLabel("LOGOUT");
		navigationLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationLabel.setFont(UI.APPLICATION_THEME_FONT_12_BOLD);
		navigationLabel.setBounds(UI.NAVIGATION_PANEL_BUTTON_TEXT_X_AXIS, UI.NAVIGATION_PANEL_BUTTON_TEXT_Y_AXIS,
				UI.NAVIGATION_PANEL_BUTTON_TEXT_WIDTH, UI.NAVIGATION_PANEL_BUTTON_TEXT_HEIGHT);
		panel.add(navigationLabel);

		// Adding navigation button(JPanel) icon to panel
		JLabel navigationIcon = new JLabel("");
		navigationIcon.setHorizontalAlignment(SwingConstants.CENTER);
		navigationIcon.setIcon(new ImageIcon(PlayerGUI.class.getResource("/com/funquiz/resources/logout_icon.png")));
		navigationIcon.setBounds(UI.NAVIGATION_PANEL_BUTTON_ICON_X_AXIS, UI.NAVIGATION_PANEL_BUTTON_ICON_Y_AXIS,
				UI.NAVIGATION_PANEL_BUTTON_ICON_WIDTH, UI.NAVIGATION_PANEL_BUTTON_ICON_HEIGHT);
		panel.add(navigationIcon);
	}

	/*
	 * getNavigation : Method is used to return the JPanel which wrapped by
	 * NavigationPanel
	 * 
	 * @return JPanel which contains the sub elements with added styling
	 */
	@Override
	public JPanel getNavigation() {
		return panel;
	}
}
