package com.funquiz.peripherals;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A Graphical User Interface to start and stop RMI server
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class ServerGUI extends JFrame {
	
	private static final long serialVersionUID = 3795878569275692801L;
	
	/**
	 * Text label for the server button
	 */
	private JLabel serverButtonLabel;
	
	/**
	 * Text Pane for the server status log
	 */
	private JTextPane serverLogTextPlane;
	
	/**
	 * JPanel for the server button
	 */
	private JPanel serverButtonPanel;

	/**
	 * Initialize JPanel and required properties and add UI elements to the JFrame
	 */
	public ServerGUI() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				setButtonText("Stop Server");
			}
			@Override
			public void keyTyped(KeyEvent e) {
				setButtonText("Stop Server");
			}
		});
		// Defining the JFrame properties
		setTitle("FUNQUIZ | SERVER");
		setBounds(100, 100, 505, 246);

		// Creating a JPanel to containt all the sub elements, panel buttons, message and icon
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(UI.COLOR_WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		// Adding a JScrollPane incase of an overflow with the message
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(102, 11, 377, 129);
		contentPanel.add(scrollPane);

		// Defining error message in JTextPane
		serverLogTextPlane = new JTextPane();
		serverLogTextPlane.setText("Press start server to initiate RMI Server");
		serverLogTextPlane.setEditable(false);
		serverLogTextPlane.setForeground(UI.COLOR_DARK_GREY);
		serverLogTextPlane.setSelectionColor(UI.COLOR_WHITE);
		scrollPane.setViewportView(serverLogTextPlane);
		serverLogTextPlane.setFont(UI.APPLICATION_THEME_FONT_13_PLAIN);

		// Adding icon
		JLabel serverIcon = new JLabel();
		serverIcon.setIcon(new ImageIcon(ServerGUI.class.getResource("/com/funquiz/resources/server_icon.png")));
		serverIcon.setBounds(21, 21, 50, 50);
		contentPanel.add(serverIcon);

		// Adding a new JPanel to set the look and feel of the pop-up like native windows pop-up
		JPanel footerPanel = new JPanel();
		footerPanel.setBounds(0, 151, 489, 56);
		contentPanel.add(footerPanel);
		footerPanel.setLayout(null);

		// Styling JPanel as a button
		serverButtonPanel = new JPanel();
		serverButtonPanel.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		serverButtonPanel.setBorder(new LineBorder(UI.COLOR_DARK_GREY));
		serverButtonPanel.setBackground(UI.COLOR_DARK_GREY);
		serverButtonPanel.setBounds(372, 11, 107, 32);
		serverButtonPanel.setLayout(null);
		
		serverButtonLabel = new JLabel("Start Server");
		serverButtonLabel.setBounds(0, 0, 107, 32);
		serverButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		serverButtonLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		serverButtonLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		serverButtonLabel.setForeground(UI.COLOR_WHITE);
		serverButtonPanel.add(serverButtonLabel);
		
		footerPanel.add(serverButtonPanel);
	}

	/**
	 * To update status log containg the latest status of the RMI server initialization
	 * 
	 * @param statusUpdate Indicates the latest status of the RMI server initialization
	 */
	public void setServerStatus(String statusUpdate) {
		if (serverLogTextPlane.getText().equals("Press start server to initiate RMI Server")) {
			serverLogTextPlane.setText("");
		}
		serverLogTextPlane.setText(serverLogTextPlane.getText() + statusUpdate + "\n");
	}

	/**
	 * Clears the server status log and set it to default 
	 */
	public void clearServerStatus() {
		serverLogTextPlane.setText("Press start server to initiate RMI Server");
	}

	/**
	 * To set the text of the server button to either 'Start server' to 'Stop Server'
	 * 
	 * @param btnText Text of the button to be set
	 */
	public void setButtonText(String btnText) {
		if(btnText.equals("Start Server")) {
			serverButtonPanel.setBackground(UI.COLOR_DARK_GREY);
			serverButtonLabel.setForeground(UI.COLOR_WHITE);
		} else {
			serverButtonPanel.setBackground(UI.COLOR_RED);
			serverButtonLabel.setForeground(UI.COLOR_WHITE);
		}
		serverButtonLabel.setText(btnText);
	}
	
	/**
	 * Returns the JPanel of which that acts as the server button 
	 * 
	 * @return JPanel of which acts as the server button
	 */
	public JPanel getServerButton () {
		return serverButtonPanel;
	}
	
	/**
	 * Returns a String which contains the text of the server button
	 * 
	 * @return Text of the server button
	 */
	public String getServerButtonLabel() {
		return serverButtonLabel.getText();
	}
}
