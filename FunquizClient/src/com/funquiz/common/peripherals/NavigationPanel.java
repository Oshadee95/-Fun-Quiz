package com.funquiz.common.peripherals;

import javax.swing.JPanel;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public abstract class NavigationPanel extends JPanel {

	/**
	 * This will return the sub elements added to the JPanel. Contains all the
	 * elements which is displayed on the left side of the application
	 * 
	 * @return JPanel which is wrapped by a NavigationPanel(JPanel with an extra
	 *         method)
	 */
	public abstract JPanel getNavigation();
}
