package com.funquiz.login.peripherals;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.funquiz.admin.peripherals.AdminGUI;
import com.funquiz.common.peripherals.ContentPanel;
import com.funquiz.common.peripherals.ErrorNotifier;
import com.funquiz.common.peripherals.UI;
import com.funquiz.connectivity.FunquizClient;
import com.funquiz.models.User;
import com.funquiz.player.peripherals.PlayerGUI;
import javax.swing.border.MatteBorder;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public class LoginContentGUI extends ContentPanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain the sub
	// elements with their styling and properties
	private JPanel contentPanel = new JPanel();

	// Declaring element properties need to sign-in a user
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JPanel loginButton;
	private JLabel signinLabel;
	private JLabel lblNeedAnAccount;

	/*
	 * Method BarChartPanel : used to initialize JFrame, required properties and add
	 * UI elements to the JFrame
	 * 
	 * @params chartTitle The title used to name the graph, heading
	 * 
	 * @params xAxis Title at the bottom of the graph
	 * 
	 * @params dataset Title at the left grid line of the graph
	 */
	public LoginContentGUI() {
		/*
		 * Adding elements to login content panel Login content panel size will the the
		 * same as the application size
		 */
		contentPanel.setName("login");
		contentPanel.setLayout(null);
		contentPanel.setBounds(0, 0, 383, 672);
		contentPanel.setBackground(Color.WHITE);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(LoginContentGUI.class.getResource("/com/funquiz/resources/logo.png")));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setBounds(79, 113, 226, 54);
		contentPanel.add(logo);

		// Adding username label
		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		usernameLabel.setBounds(60, 260, 67, 22);
		contentPanel.add(usernameLabel);

		// Adding username text-field
		usernameTextField = new JTextField();
		usernameTextField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		usernameTextField.setForeground(UI.APPLICATION_THEME_QUATERNARY_COLOR);
		usernameTextField.setMargin(new Insets(2, 10, 2, 2));
		usernameTextField.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		usernameTextField.setBounds(60, 283, 280, 34);
		contentPanel.add(usernameTextField);
		usernameTextField.setColumns(10);

		// Adding password label
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		passwordLabel.setBounds(60, 343, 67, 22);
		contentPanel.add(passwordLabel);

		// Adding password field
		passwordField = new JPasswordField();
		passwordField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		passwordField.addKeyListener(new KeyAdapter() {
			/*
			 * Method keyPressed to handle keyboard press events The key listner will listed
			 * whether the key pressed is the 'Enter' key on key press, to execute the if
			 * block
			 * 
			 * @param arg0 to get information about the mosue click
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				// 10 == 'Enter' key
				if (e.getKeyCode() == 10) {
					authenticateUser();
				}
			}
		});
		passwordField.setForeground(UI.APPLICATION_THEME_QUATERNARY_COLOR);
		passwordField.setMargin(new Insets(2, 10, 2, 2));
		passwordField.setBounds(60, 365, 280, 34);
		passwordField.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		passwordField.setColumns(10);
		contentPanel.add(passwordField);

		// Adding signin button
		loginButton = new JPanel();
		loginButton.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		loginButton.setBorder(new LineBorder(UI.CONTENT_PANEL_SELECTED_TAB_COLOR));
		loginButton.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		loginButton.setBounds(223, 448, 117, 44);
		contentPanel.add(loginButton);
		loginButton.setLayout(null);

		// Adding singin text inside signin button
		signinLabel = new JLabel("Sign In");
		signinLabel.setBounds(31, 11, 63, 20);
		signinLabel.setFont(UI.APPLICATION_THEME_FONT_17_PLAIN);
		signinLabel.setForeground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
		loginButton.add(signinLabel);

		/*
		 * Implementing mouse listeners to enhance user experience Enhancement
		 * implementation : changing signin button and button text color as mouse enters
		 * and leaves the signin button Signin implementation to validate user
		 * credentials added on mouse click
		 */
		loginButton.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseEntered to handle mouse click events loginButton and signinLabel
			 * will change color accordingly on mouse exit to enhance UX, user will know
			 * that panel can be clicked
			 * 
			 * @param arg0 to get information about the mosue click
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setBorder(new LineBorder(UI.APPLICATION_THEME_PRIMARY_COLOR));
				loginButton.setBackground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
				signinLabel.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
			}

			/*
			 * Method mouseExited to handle mouse click events loginButton and signinLabel
			 * will change color accordingly on mouse exit to enhance UX, user will know
			 * that mouse is not in click range
			 * 
			 * @param arg0 to get information about the mosue click
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBorder(new LineBorder(UI.CONTENT_PANEL_SELECTED_TAB_COLOR));
				loginButton.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
				signinLabel.setForeground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
			}

			/*
			 * Method mouseClicked to handle mouse click events Method authenticateUser will
			 * be called on mouse click
			 * 
			 * @param arg0 to get information about the mosue click
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				authenticateUser();
			}
		});

		lblNeedAnAccount = new JLabel("Need an account ?");
		lblNeedAnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginGUI.selectedContent = new RegisterContentGUI();
				LoginGUI.setSelectedPanel();
			}
		});
		lblNeedAnAccount.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNeedAnAccount.setBounds(223, 503, 117, 22);
		contentPanel.add(lblNeedAnAccount);

	}

	/*
	 * Method authenticateUser : used to validate the credentials of the signing in
	 * user and direct him/her accordingly to their roles and if not display
	 * relevant error messages
	 */
	public void authenticateUser() {
		try {
			// https://stackoverflow.com/questions/10443308/why-gettext-in-jpasswordfield-was-deprecated
			if (usernameTextField.getText().length() > 4 && String.valueOf(passwordField.getPassword()).length() > 5) {
				FunquizClient.authUser = FunquizClient.funquizInterface.authenticateUser(
						new User(usernameTextField.getText(), String.valueOf(passwordField.getPassword())));
				if (FunquizClient.authUser != null) {
					if (FunquizClient.authUser.getRole().equals("PLAYER")) {
						FunquizClient.playerGUI = new PlayerGUI();
						FunquizClient.playerGUI.setVisible(true);
						FunquizClient.loginGUI.dispose();
					} else if (FunquizClient.authUser.getRole().equals("ADMIN")) {
						FunquizClient.adminGUI = new AdminGUI();
						FunquizClient.adminGUI.setVisible(true);
						FunquizClient.loginGUI.dispose();
					}
				} else {
					new ErrorNotifier("Incorrect username or password").setVisible(true);
				}
			} else {
				new ErrorNotifier("Required fields are missing").setVisible(true);
			}
		} catch (Exception e) {
			new ErrorNotifier(e.getCause().getMessage()).setVisible(true);
			e.printStackTrace();
		}
	}

	/*
	 * Method getContent is implemented to return JPanel inside ContentPanel
	 * 
	 * @returns JPanel Contains completed layout of with the add sub elements
	 */
	@Override
	public JPanel getContent() {
		return contentPanel;
	}
}
