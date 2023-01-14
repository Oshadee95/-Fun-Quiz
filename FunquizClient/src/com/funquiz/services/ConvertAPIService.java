package com.funquiz.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import com.convertapi.Config;
import com.convertapi.ConvertApi;
import com.convertapi.Param;
import com.funquiz.models.QuestionReport;
import com.funquiz.models.QuizReport;

/**
 * ConvertAPIService class to save the results report on to a pdf formatted document
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class ConvertAPIService {

	/**
	 * Default path where the generated pdf file will be saved
	 */
	private static final String REPORT_FILE_PATH = System.getProperty("user.home") + "/Desktop/Funquiz-Reports";
	
	/**
	 * Temp API key to use ConverAPI api (1500 free convertions) 
	 */
	private static final String API_SECRET = "6ltjf19DFxKG36b9";

	/**
	 * @link https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java
	 */
	public ConvertAPIService() {
		File theDir = new File(REPORT_FILE_PATH);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}

	/**
	 * To generate the quiz results to a html table
	 * 
	 * @param quizReport Results of the quiz
	 * @return htmlReportInString The html table to be saved
	 */
	private String getHTMLReport(QuizReport quizReport) {
		String htmlReport = "<!DOCTYPE html><html><head><style>table, th, td {border: 1px solid black;border-collapse: collapse;width: 100%;max-width: 85%; margin: auto; text-align: center; height: 50px; font-size: 24px;}td {width: 50%; height: 50px;}</style></head><body><table><tr><th colspan=\"2\">RESULTS</th></tr>";
		for (QuestionReport question : quizReport.getQuestionReports()) {
			htmlReport += "<tr><td>" + question.getQuestionId() + "</td><td >" + question.getResult() + "</td></tr>";
		}
		htmlReport += "<tr><td>TOTAL</td><td >" + quizReport.getScore() + "%</td></tr>";
		htmlReport += "</table></body></html>";
		return htmlReport;
	}

	/**
	 * To save the generated html table on to a hmtl file in the windows temp folder
	 * 
	 * @link https://www.journaldev.com/878/java-write-to-file
	 * @link https://mkyong.com/java/how-to-get-the-temporary-file-path-in-java
	 * @param htmlReport The generated html table from getHTMLReport method
	 * @return tempHtmlReportPath The file name and path where the html file is saved
	 * @throws IOException Throw a failure in Input & Output operations
	 */
	private Path saveTempFileToDisk(String htmlReport) throws IOException {
		Path tempPath = Files.createTempFile("", ".html");
		Files.write(tempPath, htmlReport.getBytes());
		return tempPath;
	}

	/**
	 * To call the ConverAPI web api and conver the generated html file to a pdf formatted document
	 * 
	 * @param tempPathToFile Path to the file in the windows temp folder
	 * @return fileSaveResult Boolean to whether the file was saved or not
	 * @throws InterruptedException Thrown to indicate that an input or output transfer has been terminated because the thread performing it was interrupted
	 * @throws ExecutionException Thrown when attempting to retrieve the result of a task that aborted by throwing an exception
	 * @throws IOException Throw a failure in Input & Output operations
	 */
	private boolean saveReportToDisk(Path tempPathToFile) throws InterruptedException, ExecutionException, IOException {
		Config.setDefaultSecret(API_SECRET);
		return (ConvertApi.convert("html", "pdf", new Param("File", tempPathToFile)).get()
				.saveFilesSync(Paths.get(REPORT_FILE_PATH)) != null);
	};

	/**
	 * To delete the generated html file from the windows temp folder
	 * 
	 * @link https://mkyong.com/java/how-to-delete-temporary-file-in-java
	 * @param tempFilePath Path of the html file in windows temp folder to be deleted
	 * @return fileDeleteResult Boolean to whether html file in windows temp folder was deleted or not 
	 * @throws IOException Throw a failure in Input & Output operations
	 */
	private boolean deleteFileFromTemp(Path tempFilePath) throws IOException {
		return Files.deleteIfExists(tempFilePath);
	}

	/**
	 * To print a pdf formatted document of the quiz results
	 * 
	 * @param quizReport Results of the quiz
	 * @return reportPrintResult Boolean to whether html file in windows temp folder was deleted or not
	 * @throws IOException Throw a failure in Input & Output operations
	 * @throws InterruptedException Thrown to indicate that an input or output transfer has been terminated because the thread performing it was interrupted
	 * @throws ExecutionException Thrown when attempting to retrieve the result of a task that aborted by throwing an exception
	 */
	public boolean printReport(QuizReport quizReport) throws IOException, InterruptedException, ExecutionException {
		String htmlReport = this.getHTMLReport(quizReport); // Generating report in hmtl format
		Path tempFilePath = this.saveTempFileToDisk(htmlReport); // Saving temp html file to local disk temp folder
		saveReportToDisk(tempFilePath); // Saving final pdf file to local disk
		return deleteFileFromTemp(tempFilePath); // Deleting file from temp path
	}
}
