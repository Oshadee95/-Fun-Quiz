package com.funquiz.player.peripherals;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.funquiz.common.peripherals.ContentPanel;
import com.funquiz.common.peripherals.UI;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public class QuizContentGUI extends ContentPanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain
	// navigationIndicatorPanel and questionBodyPanel
	private JPanel contentPanel = new JPanel();

	// Declaring and initializing new JPanel to act as an wrapper to contain card
	// JPanels
	private JPanel quizBodyPanel = new JPanel();

	List<JButton> jButtonList = new ArrayList<JButton>();

	int selectedQuestionnaireSize = 10;

	/*
	 * DashboardContentPanel method : used to initialize ContentPanel, required
	 * properties and add UI elements to the ContentPanel
	 */
	public QuizContentGUI() {
		// Adding elements to the ContentPanel
		setContentPanel();
	}

	/*
	 * Method setContentPanel adds swing/awt and other elements to the ContentPanel
	 */
	private void setContentPanel() {
		initializeContentPanel();
		addNavigationIndicator();
	}

	/*
	 * Method initializeContentPanel adds the necessary UI layout(styling) to the
	 * ContentPanel UI layout categorized as JPanel
	 * layout/boundaries/background-color
	 */
	private void initializeContentPanel() {
		contentPanel.setBounds(UI.CONTENT_PANEL_X_AXIS, UI.CONTENT_PANEL_Y_AXIS, UI.CONTENT_PANEL_WIDTH,
				UI.CONTENT_PANEL_HEIGHT);
		contentPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		contentPanel.setLayout(null);
		quizBodyPanel.setBorder(null);
		quizBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		quizBodyPanel.setBounds(UI.CONTENT_PANEL_BODY_X_AXIS, UI.CONTENT_PANEL_BODY_Y_AXIS, UI.CONTENT_PANEL_BODY_WIDTH,
				UI.CONTENT_PANEL_BODY_HEIGHT);
		contentPanel.add(quizBodyPanel);
		quizBodyPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Select the Number of Questions");
		lblNewLabel.setBounds(336, 78, 236, 28);
		lblNewLabel.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		quizBodyPanel.add(lblNewLabel);

		JButton btnNewButton = new JButton("Start Quiz");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnNewButton.setContentAreaFilled(false);
				btnNewButton.setOpaque(true);
				btnNewButton.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);

				PlayerGUI.selectedContent = new QuestionnaireContentGUI(selectedQuestionnaireSize);
				PlayerGUI.setSelectedPanel();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnNewButton.setContentAreaFilled(false);
				btnNewButton.setOpaque(true);
				btnNewButton.setBackground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
			}
		});

		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(362, 455, 199, 65);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		btnNewButton.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		btnNewButton.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		quizBodyPanel.add(btnNewButton);

		setQuestionnaireButtons();
	}

	private void setQuestionnaireButtons() {

		int btnIndex = 10;
		// For row 1 and row 2
		for (int i = 176; i < 255;) {
			for (int j = 160; j < 668;) {
				JButton jButton = new JButton(String.valueOf(btnIndex));
				jButton.setBackground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
				jButton.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
				jButton.setFont(new Font("Roboto", Font.PLAIN, 16));
				jButton.setFocusPainted(false);
				jButton.setBorderPainted(false);
				jButton.setBorder(null);
				jButton.setBounds(j, i, 80, 55);
				jButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateQuestionButtonUI(jButton);
					}
				});
				quizBodyPanel.add(jButton);
				jButtonList.add(jButton);

				if (btnIndex < 50) {
					btnIndex = btnIndex + 10;
				} else if (btnIndex < 100) {
					btnIndex = btnIndex + 25;
				} else {
					btnIndex = btnIndex + 100;
				}
				j = j + 101;
			}
			i = i + 78;
		}

		// For row 3
		for (int i = 331; i < 332; i++) {
			for (int j = 261; j < 565;) {
				JButton jButton = new JButton(String.valueOf(btnIndex));
				jButton.setBackground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
				jButton.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
				jButton.setFont(new Font("Roboto", Font.PLAIN, 16));
				jButton.setFocusPainted(false);
				jButton.setBorderPainted(false);
				jButton.setBorder(null);
				jButton.setBounds(j, i, 80, 55);
				jButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateQuestionButtonUI(jButton);
					}
				});
				quizBodyPanel.add(jButton);
				jButtonList.add(jButton);
				btnIndex = btnIndex + 100;
				j = j + 101;
			}
		}
	}

	private void updateQuestionButtonUI(JButton selectedButton) {
		for (JButton jButton : jButtonList) {
			if (jButton.getText().equals(selectedButton.getText())) {
				selectedQuestionnaireSize = Integer.parseInt(jButton.getText());
				jButton.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
			} else {
				jButton.setBackground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
			}
		}
	}

	/*
	 * Method addNavigationIndicator adds UI layout(styling) to
	 * navigationIndicatorPanel which shows the current navigated panel on the top
	 * of ContentPanel navigationIndicatorPanel is a sub element under ContentPanel
	 * UI layout categorized as JPanel layout/boundaries/background-color, JLabel
	 * text/text-color/font-size/boundaries
	 */
	private void addNavigationIndicator() {
		JPanel navigationIndicatorPanel = new JPanel();
		navigationIndicatorPanel.setBorder(UI.NAVIGATION_INDICATOR_PANEL_BORDER);
		navigationIndicatorPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		navigationIndicatorPanel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_X_AXIS, UI.NAVIGATION_INDICATOR_PANEL_Y_AXIS,
				UI.NAVIGATION_INDICATOR_PANEL_WIDTH, UI.NAVIGATION_INDICATOR_PANEL_HEIGHT);
		contentPanel.add(navigationIndicatorPanel);
		navigationIndicatorPanel.setLayout(null);

		JLabel navigationIndicatorMainLabel = new JLabel("Player /");
		navigationIndicatorMainLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_USER_TYPE_X_AXIS,
				UI.NAVIGATION_INDICATOR_PANEL_USER_TYPE_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_USER_TYPE_WIDTH,
				UI.NAVIGATION_INDICATOR_PANEL_LABEL_HEIGHT);
		navigationIndicatorMainLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorMainLabel.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorMainLabel);

		JLabel navigationIndicatorActiveLabel = new JLabel("Quiz");
		navigationIndicatorActiveLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		navigationIndicatorActiveLabel.setBounds(UI.NAVIGATION_INDICATOR_PANEL_CURRENT_WINDOW_X_AXIS,
				UI.NAVIGATION_INDICATOR_PANEL_CURRENT_WINDOW_Y_AXIS, UI.NAVIGATION_INDICATOR_PANEL_CURRENT_WINDOW_WIDTH,
				UI.NAVIGATION_INDICATOR_PANEL_LABEL_HEIGHT);
		navigationIndicatorActiveLabel.setForeground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
		navigationIndicatorPanel.add(navigationIndicatorActiveLabel);
	}

	/*
	 * Method getContent is implemented to return JPanel inside ContentPanel
	 * 
	 * @returns JPanel Contains completed layout of with the add sub elements
	 */
	public JPanel getContent() {
		return contentPanel;
	}
}
