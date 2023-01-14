package com.funquiz.player.peripherals;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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
import com.funquiz.models.Game;
import com.funquiz.models.QuizReport;
import com.funquiz.services.ConvertAPIService;
import com.funquiz.utils.QuizResultFomat;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public class ResultContentGUI extends ContentPanel {

	// Declaring and initializing new JPanel to act as an wrapper to contain
	// navigationIndicatorPanel and questionBodyPanel
	private JPanel contentPanel = new JPanel();

	// Declaring and initializing new JPanel to act as an wrapper to contain card
	// JPanels
	private JPanel dashboardBodyPanel = new JPanel();
	private ContentTable table = new ContentTable();

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

	/*
	 * DashboardContentPanel method : used to initialize ContentPanel, required
	 * properties and add UI elements to the ContentPanel
	 */
	public ResultContentGUI() {
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);

		// Creating a new DefaultTableModel to declare the column names
		DefaultTableModel model = new DefaultTableModel(
				new String[] { "Date", "Time", "Questionnaire Size", "Score", "", "" }, 0);

		try {

			for (Game game : FunquizClient.funquizInterface.getPreviousGamesByUser(FunquizClient.authUser)) {
				model.addRow(
						new Object[] { dateFormat.format(game.getPlayedDate()), timeFormat.format(game.getPlayedDate()),
								game.getQuizSize(), game.getQuizScore() + "%", "PRINT RESULT", game.getQuizResults() });
			}

		} catch (Exception e) {
			new ErrorNotifier(e.getCause().getMessage()).setVisible(true);
			e.printStackTrace();
		}

		table.addMouseListener(new MouseAdapter() {
			/*
			 * Method mouseClicked to handle mouse click events Results repot will be
			 * printed on double mouse click
			 * 
			 * @param mouseEvent to get information about the mosue click
			 */
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				// Checking if the click event has a count > 2 to execute the following, and
				// selected row is not empty/null
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					QuizReport quizReport = new QuizReport();
					quizReport.setQuestionReports(QuizResultFomat.format(table.getModel().getValueAt(table.getSelectedRow(), 5).toString()));
					String quizScore = table.getModel().getValueAt(table.getSelectedRow(), 3).toString();
					quizReport.setScore(quizScore.substring(0, quizScore.length() - 1));

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
		columnModel.getColumn(3).setCellRenderer(centerAlingedCell);
		columnModel.getColumn(4).setCellRenderer(centerAlingedCell);

		// Hide column for UI
		columnModel.removeColumn(columnModel.getColumn(5));

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

		scrollPane.setBounds(32, 87, 885, 491);
		dashboardBodyPanel.add(scrollPane);

		scrollPane.setViewportView(table);

		JButton btnLeaderboard = new JButton("Leaderboard");
		btnLeaderboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerGUI.selectedContent = new LeaderboardContentGUI();
				PlayerGUI.setSelectedPanel();
			}
		});
		btnLeaderboard.setForeground(Color.WHITE);
		btnLeaderboard.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnLeaderboard.setFocusPainted(false);
		btnLeaderboard.setBorderPainted(false);
		btnLeaderboard.setBorder(null);
		btnLeaderboard.setBackground(new Color(38, 50, 56));
		btnLeaderboard.setBounds(718, 11, 199, 65);
		dashboardBodyPanel.add(btnLeaderboard);
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

		JLabel navigationIndicatorActiveLabel = new JLabel("Result");
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
