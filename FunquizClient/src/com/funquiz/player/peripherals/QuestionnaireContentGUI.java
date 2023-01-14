package com.funquiz.player.peripherals;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.funquiz.common.peripherals.ContentPanel;
import com.funquiz.common.peripherals.ErrorNotifier;
import com.funquiz.common.peripherals.UI;
import com.funquiz.connectivity.FunquizClient;
import com.funquiz.models.Question;
import com.funquiz.models.QuizReport;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public class QuestionnaireContentGUI extends ContentPanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain
	// navigationIndicatorPanel and questionBodyPanel
	private JPanel contentPanel = new JPanel();

	// Declaring and initializing new JPanel to act as an wrapper to contain card
	// JPanels
	private JPanel questionnaireBodyPanel = new JPanel();

	List<JButton> jButtonList = new ArrayList<JButton>();

	JLabel questionIcon = new JLabel("");

	int questionIndex = -1;
	JLabel lblNewLabel_1 = new JLabel("1");
	List<Question> questionnaire;

	/*
	 * DashboardContentPanel method : used to initialize ContentPanel, required
	 * properties and add UI elements to the ContentPanel
	 */
	public QuestionnaireContentGUI(int selectedQuestionnaireSize) {

		try {
			questionnaire = FunquizClient.funquizInterface.getQuizQuestionnaire(selectedQuestionnaireSize);
		} catch (Exception e) {
			new ErrorNotifier(e.getCause().getMessage()).setVisible(true);
			e.printStackTrace();
		}

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

		JButton btnNewButton = new JButton("Skip Question");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNextQuestion(-1);
			}
		});
		btnNewButton.setBounds(735, 506, 184, 70);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBackground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
		btnNewButton.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		btnNewButton.setFont(UI.APPLICATION_THEME_FONT_16_PLAIN);
		questionnaireBodyPanel.add(btnNewButton);

		setQuestionnaireButtons();

		questionIcon.setBorder(new LineBorder(UI.APPLICATION_THEME_PRIMARY_COLOR));
		questionIcon.setBounds(34, 24, 672, 465);
		questionIcon.setHorizontalAlignment(SwingConstants.CENTER);
		questionIcon.setIcon(new ImageIcon(questionnaire.get(questionIndex + 1).getUrl()));
		questionnaireBodyPanel.add(questionIcon);

		JLabel lblNewLabel = new JLabel("No of questions : ");
		lblNewLabel.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		lblNewLabel.setBounds(34, 523, 130, 14);
		questionnaireBodyPanel.add(lblNewLabel);

		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		lblNewLabel_1.setBounds(145, 523, 30, 14);
		questionnaireBodyPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("/ " + this.questionnaire.size());
		lblNewLabel_2.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		lblNewLabel_2.setBounds(185, 523, 46, 14);
		questionnaireBodyPanel.add(lblNewLabel_2);

		JButton btnNewButton_1 = new JButton("Cancel Quiz");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerGUI.selectedContent = new QuizContentGUI();
				PlayerGUI.setSelectedPanel();
			}
		});
		btnNewButton_1.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setForeground(UI.APPLICATION_THEME_PRIMARY_COLOR);
		btnNewButton_1.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		btnNewButton_1.setBounds(735, 24, 184, 70);
		questionnaireBodyPanel.add(btnNewButton_1);
		
		for (Question question : questionnaire) {
			System.out.println(question.getUrl());
		}
	}

	private void setQuestionnaireButtons() {
		int btnIndex = 0;
		for (int i = 105; i < 420;) {
			for (int j = 735; j < 840;) {
				JButton jButton = new JButton(String.valueOf(btnIndex));
				jButton.setBackground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
				jButton.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
				jButton.setFont(new Font("Roboto", Font.PLAIN, 16));
				jButton.setFocusPainted(false);
				jButton.setBorderPainted(false);
				jButton.setBorder(null);
				jButton.setBounds(j, i, 80, 70);
				jButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setNextQuestion(Integer.parseInt(e.getActionCommand()));
					}
				});
				questionnaireBodyPanel.add(jButton);
				jButtonList.add(jButton);

				j = j + 104;
				btnIndex++;
			}
			i = i + 78;
		}
	}

	private void setNextQuestion(int selectedSolution) {
		if (questionIndex != -1 && questionIndex < questionnaire.size()) {
			questionnaire.get(questionIndex).setSolution(selectedSolution);
			questionIndex++;

			if (questionIndex == questionnaire.size()) {
				try {
					QuizReport quizReport = FunquizClient.funquizInterface.getQuizResults(FunquizClient.authUser, questionnaire);
					PlayerGUI.selectedContent = new PrintResultContentGUI(quizReport);
					PlayerGUI.setSelectedPanel();

				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				questionIcon.setIcon(new ImageIcon(questionnaire.get(questionIndex).getUrl()));
				questionnaireBodyPanel.add(questionIcon);
				lblNewLabel_1.setText(questionIndex + 1 + "");
				questionnaireBodyPanel.add(lblNewLabel_1);
				questionnaireBodyPanel.repaint();
			}
		} else {
			questionIndex++;
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
