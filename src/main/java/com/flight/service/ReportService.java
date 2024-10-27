package com.flight.service;

import java.util.List;

import com.flight.exception.ReportException;
import com.flight.model.Report;

public interface ReportService {

	/*
	 * All Functions ->
	 * Administrator Access
	 */
	Report addReport(Report report, String authKey) throws ReportException;
	List<Report> viewAllReports(String authKey) throws ReportException;
	Report deleteReport(Integer reportId, String authKey) throws ReportException;
	
}
