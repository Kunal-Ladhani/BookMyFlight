package com.bus.service;

import java.util.List;

import com.bus.exception.ReportException;
import com.bus.model.Report;

public interface ReportService {

	/*
	 * All Functions ->
	 * Administrator Access
	 */
	Report addReport(Report report, String authKey) throws ReportException;
	List<Report> viewAllReports(String authKey) throws ReportException;
	Report deleteReport(Integer reportId, String authKey) throws ReportException;
	
}
