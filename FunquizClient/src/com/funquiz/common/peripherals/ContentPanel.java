package com.funquiz.common.peripherals;

import javax.swing.JPanel;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public abstract class ContentPanel extends JPanel {

	/**
	 * Method getContent : used to return the sub elements added to the JPanel.
	 * Contains all the elements which is displayed on the right side of the
	 * application
	 * 
	 * @return JPanel which is wrapped by a ContentPanel(JPanel with an extra method)
	 */
	public abstract JPanel getContent();
}
