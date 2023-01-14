package com.funquiz.admin.peripherals;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.funquiz.common.peripherals.ContentPanel;
import com.funquiz.common.peripherals.ContentTable;
import com.funquiz.common.peripherals.ErrorNotifier;
import com.funquiz.common.peripherals.SuccessNotifier;
import com.funquiz.common.peripherals.UI;
import com.funquiz.connectivity.FunquizClient;
import com.funquiz.models.Question;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Insets;

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
	private JPanel dashboardBodyPanel = new JPanel();
	private ContentTable table = new ContentTable();
	private JTextField textField;
	JScrollPane scrollPane = new JScrollPane();
	JButton btnCreate;
	int selectedQuestion;

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

		dashboardBodyPanel.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		dashboardBodyPanel.setBounds(UI.CONTENT_PANEL_BODY_X_AXIS, UI.CONTENT_PANEL_BODY_Y_AXIS,
				UI.CONTENT_PANEL_BODY_WIDTH, UI.CONTENT_PANEL_BODY_HEIGHT);
		contentPanel.add(dashboardBodyPanel);
		dashboardBodyPanel.setLayout(null);

		btnCreate = new JButton("Create Question");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					if (btnCreate.getText().equalsIgnoreCase("Create Question")) {
						if (FunquizClient.funquizInterface.addQuestion(new Question(new URL(textField.getText())))) {
							new SuccessNotifier("Question successfully created", null, null).setVisible(true);
						} else {
							new ErrorNotifier("Failed to create question").setVisible(true);
						}
					} else {
						if (btnCreate.getText().equalsIgnoreCase("Update Question")) {
							if (FunquizClient.funquizInterface.updateQuestion(new Question(selectedQuestion, new URL(textField.getText())))) {
								new SuccessNotifier("Question successfully updated", null, null).setVisible(true);
							} else {
								new ErrorNotifier("Failed to update question").setVisible(true);
							}
						} else if (btnCreate.getText().equalsIgnoreCase("Remove Question")) {
							if (FunquizClient.funquizInterface.removeQuestion(new Question(selectedQuestion))) {
								new SuccessNotifier("Question successfully removed", null, null).setVisible(true);
							} else {
								new ErrorNotifier("Failed to remove question").setVisible(true);
							}
						}
						
						// After executing the action, button will be set to its default state (create
						// button)
						btnCreate.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
						btnCreate.setText("Create Question");
						textField.setText("");
						displayQuestion();
					}
				} catch (Exception e) {
					new ErrorNotifier(e.getCause().getMessage()).setVisible(true);
					e.printStackTrace();
				}
			}
		});
		btnCreate.setForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		btnCreate.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnCreate.setFocusPainted(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setBorder(null);
		btnCreate.setBackground(new Color(38, 50, 56));
		btnCreate.setBounds(718, 11, 199, 65);
		dashboardBodyPanel.add(btnCreate);

		JLabel lblQuestionUrl = new JLabel("Enter Question URL");
		lblQuestionUrl.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblQuestionUrl.setBounds(32, 11, 157, 22);
		dashboardBodyPanel.add(lblQuestionUrl);

		textField = new JTextField();
		textField.setText("");
		textField.setMargin(new Insets(2, 10, 2, 2));
		textField.setForeground(new Color(79, 91, 98));
		textField.setFont(new Font("Roboto", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField.setBounds(32, 42, 676, 34);
		textField.addKeyListener(new KeyAdapter() {
			/*
			 * Method keyTyped to handle keyboard type events displayStudentList method will
			 * be executed(repainted) on keyboard press to search of the typed text in the
			 * JTextField
			 * 
			 * @param arg0 to get information about the key press event
			 */
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (textField.getText().length() == 0) {
					displayQuestion();
					btnCreate.setText("Create Question");
					btnCreate.setBackground(UI.APPLICATION_THEME_SECONDARY_COLOR);
				}
			}
		});
		dashboardBodyPanel.add(textField);

		displayQuestion();
	}

	private void displayQuestion() {
		scrollPane.setBorder(null);

		// Creating a new DefaultTableModel to declare the column names
		DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Question URL", "Answer", "" }, 0);

		try {

			for (Question question : FunquizClient.funquizInterface.getAllQuestion()) {
				model.addRow(new Object[] { question.getId(), question.getUrl(), question.getSolution(), "EDIT" });
			}

		} catch (Exception e) {
			new ErrorNotifier(e.getCause().getMessage()).setVisible(true);
			e.printStackTrace();
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1) {
					btnCreate.setText("Update Question");
				} else if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					btnCreate.setText("Remove Question");
				}
				selectedQuestion = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				textField.setText((table.getModel().getValueAt(table.getSelectedRow(), 1).toString()));
				btnCreate.setBackground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
			}
		});

		// Styling ContentTable to enhance UX
		table.setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		table.setUpdateSelectionOnSort(false);
		table.setFocusTraversalKeysEnabled(false);
		table.setFocusable(false);
		table.setAutoCreateRowSorter(true);
		table.setRequestFocusEnabled(false);
		table.setVerifyInputWhenFocusTarget(false);
		table.setBorder(null);

		// Adding model(DefaultTableModel) the the ContentTable
		table.setModel(model);

		// DefaultTableCellRenderer object created to add alignment. In this case,
		// setting the cloumn content alignment to center
		DefaultTableCellRenderer centerAlingedCell = new DefaultTableCellRenderer();
		centerAlingedCell.setHorizontalAlignment(JLabel.CENTER);

		// Removing horizontal cell borders
		table.setShowHorizontalLines(true);

		// TableColumnModel object created to get the column structure in the
		// ContentTable
		TableColumnModel columnModel = table.getColumnModel();

		// Aligning cloumns by their index
		columnModel.getColumn(0).setCellRenderer(centerAlingedCell);
		columnModel.getColumn(1).setCellRenderer(centerAlingedCell);
		columnModel.getColumn(2).setCellRenderer(centerAlingedCell);

		table.getColumn("ID").setMaxWidth(200);
		table.getColumn("").setMaxWidth(300);

		// Setting cursor type to pointer
		table.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));

		// Styling ContentTable to enhance UX
		table.setCursor(Cursor.getPredefinedCursor(UI.APPPLICATION_THEME_SELECT_CURSOR));
		table.setFillsViewportHeight(true);
		table.setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		table.getTableHeader().setForeground(UI.APPLICATION_THEME_SECONDARY_COLOR);
		table.getTableHeader().setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		table.setSelectionBackground(UI.CONTENT_PANEL_SELECTED_TAB_COLOR);
		table.setSelectionForeground(UI.APPLICATION_THEME_TERTIARY_COLOR);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(32);
		table.setFont(UI.APPLICATION_THEME_FONT_14_PLAIN);
		table.isCellEditable(1, 1);

		scrollPane.setBounds(32, 100, 885, 478);
		dashboardBodyPanel.add(scrollPane);

		scrollPane.setViewportView(table);
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

		JLabel navigationIndicatorMainLabel = new JLabel("Admin /");
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
