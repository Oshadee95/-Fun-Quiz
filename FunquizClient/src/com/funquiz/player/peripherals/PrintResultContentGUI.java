package com.funquiz.player.peripherals;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.funquiz.common.peripherals.ContentPanel;
import com.funquiz.common.peripherals.ErrorNotifier;
import com.funquiz.common.peripherals.SuccessNotifier;
import com.funquiz.common.peripherals.UI;
import com.funquiz.models.QuizReport;
import com.funquiz.services.ConvertAPIService;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public class PrintResultContentGUI extends ContentPanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain
	// navigationIndicatorPanel and questionBodyPanel
	private JPanel contentPanel = new JPanel();

	// Declaring and initializing new JPanel to act as an wrapper to contain card
	// JPanels
	private JPanel questionnaireBodyPanel = new JPanel();

	QuizReport quizReport;

	/*
	 * DashboardContentPanel method : used to initialize ContentPanel, required
	 * properties and add UI elements to the ContentPanel
	 */
	public PrintResultContentGUI(QuizReport quizReport) {
		this.quizReport = quizReport;
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
		questionnaireBodyPanel.setBorder(null);

		questionnaireBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		questionnaireBodyPanel.setBounds(UI.CONTENT_PANEL_BODY_X_AXIS, UI.CONTENT_PANEL_BODY_Y_AXIS,
				UI.CONTENT_PANEL_BODY_WIDTH, UI.CONTENT_PANEL_BODY_HEIGHT);
		contentPanel.add(questionnaireBodyPanel);
		questionnaireBodyPanel.setLayout(null);

		JButton retakeQuizButton = new JButton("Retake Quiz");
		retakeQuizButton.setBorderPainted(false);
		retakeQuizButton.setBorder(null);
		retakeQuizButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerGUI.selectedContent = new QuizContentGUI();
				PlayerGUI.setSelectedPanel();
			}
		});
		retakeQuizButton.setBounds(261, 306, 184, 56);
		retakeQuizButton.setFocusPainted(false);
		retakeQuizButton.setBackground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
		retakeQuizButton.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		retakeQuizButton.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		questionnaireBodyPanel.add(retakeQuizButton);

		setQuestionnaireButtons();

		JLabel scoreLabel = new JLabel("Your score is ");
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		scoreLabel.setBounds(403, 186, 130, 14);
		questionnaireBodyPanel.add(scoreLabel);

		JButton printResultsButton = new JButton("Print Results");
		printResultsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					if (new ConvertAPIService().printReport(quizReport)) {
						new SuccessNotifier("Results successfully exported as a report", null, null).setVisible(true);
					} else {
						new ErrorNotifier("Failed to export results as a report").setVisible(true);
					}
				} catch (Exception e) {
					new ErrorNotifier(e.getCause().getMessage()).setVisible(true);
					e.printStackTrace();
				}
			}
		});
		printResultsButton.setFont(new Font("Roboto", Font.PLAIN, 16));
		printResultsButton.setFocusPainted(false);
		printResultsButton.setBorderPainted(false);
		printResultsButton.setBorder(null);
		printResultsButton.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		printResultsButton.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		printResultsButton.setBounds(489, 306, 184, 56);
		questionnaireBodyPanel.add(printResultsButton);

		String formattedScore = this.quizReport.getScore().equals(".00") ? "0" : this.quizReport.getScore();
		JLabel scoreValueLabel = new JLabel(formattedScore + "%");
		scoreValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreValueLabel.setFont(UI.APPLICATION_THEME_FONT_20_PLAIN);
		scoreValueLabel.setBounds(403, 221, 130, 14);
		questionnaireBodyPanel.add(scoreValueLabel);
	}

	private void setQuestionnaireButtons() {
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
