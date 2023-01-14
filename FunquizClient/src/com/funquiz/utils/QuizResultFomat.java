package com.funquiz.utils;

import java.util.ArrayList;
import java.util.List;

import com.funquiz.models.QuestionReport;

public class QuizResultFomat {
	
	public static List<QuestionReport> format(String quizResult) {
		List<QuestionReport> questionReportList = new ArrayList<QuestionReport>();
		String[] quizResultArray = quizResult.split(",");
		
		for (String qReport : quizResultArray) {
			String[] qReportArray = qReport.split("-");
			questionReportList.add(new QuestionReport(Integer.parseInt(qReportArray[0]), qReportArray[1]));
		}
		return questionReportList;
	}

}
