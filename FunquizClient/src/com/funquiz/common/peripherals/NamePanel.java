package com.funquiz.common.peripherals;

import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.funquiz.connectivity.FunquizClient;

public class NamePanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain the sub
	// UI elements and their properties and styling
	private JPanel panel = new JPanel();

	/*
	 * NamePanel method : used to initialize JPanel, required properties and add UI
	 * elements to the JPanel
	 */
	public NamePanel() {
		/*
		 * Adding navigation button to NavigationPanel JPanel name is set to identify
		 * navigation panel when selected
		 */
		panel.setName("authUserFName");
		panel.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		panel.setBounds(0, 150, UI.NAVIGATION_PANEL_WIDTH, UI.NAVIGATION_PANEL_BUTTON_HEIGHT);
		panel.setLayout(null);
		panel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));

		// navigationLabel used to display the logged in lecturer's first and last name
		JLabel navigationLabel = new JLabel(FunquizClient.authUser.getDisplayName().toUpperCase());
		navigationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		navigationLabel.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		navigationLabel.setFont(UI.APPLICATION_THEME_FONT_17_BOLD);
		navigationLabel.setBounds(0, 0, 255, 45);
		panel.add(navigationLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(110, 43, 40, 2);
		separator.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		separator.setBackground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		panel.add(separator);
	}

	/*
	 * getName : Method is used to return the JPanel which has the styling of its
	 * sub elements (signed-in lecturer's name)
	 * 
	 * @return JPanel which contains the sub elements with added styling
	 */
	public JPanel getName() {
		return panel;
	}
}
