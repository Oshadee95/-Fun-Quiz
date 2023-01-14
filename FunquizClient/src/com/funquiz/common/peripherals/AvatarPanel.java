package com.funquiz.common.peripherals;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class AvatarPanel {

	/** Declaring and initializing new JLabel to be used for styling of avatar **/
	private JLabel icon = new JLabel("");

	/**
	 * AvatarPanel method : used to initialize JPanel, required properties and add
	 * UI elements to the JPanel
	 */
	public AvatarPanel() {
		// Adding lecturer avatar to navigation panel
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setIcon(new ImageIcon(AvatarPanel.class.getResource("/com/funquiz/resources/avatar.png")));
		icon.setBounds(52, 0, 158, 179);
	}

	/*
	 * getAvatar : Method is used to return the JPanel which has the styling of its
	 * sub elements for lecturer avatar
	 * 
	 * @return JPanel which contains the sub elements with added styling
	 */
	public JLabel getAvatar() {
		return icon;
	}
}
