package com.flight.controller;

import com.flight.exception.ReportException;
import com.flight.model.Report;
import com.flight.service.ReportService;
import com.flight.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class ReportController {

	@Autowired
	AuthenticationService service;

	@Autowired
	ReportService repoService;

	/*
	 * Show all reports
	 */

	@GetMapping("/reports")
	public ResponseEntity<List<Report>> getAllReports(@RequestParam(required = false) Integer reportId, @RequestParam(required = false) String authKey) throws ReportException {
		List<Report> reports = null;

		reports = repoService.viewAllReports(authKey);

		return new ResponseEntity<List<Report>>(reports, HttpStatus.FOUND);
	}
	/*
	 * Create a report
	 */

	@PostMapping("/addReport")
	public ResponseEntity<Report> createReport(@Valid @RequestBody Report report, @RequestParam String authKey) throws ReportException {
		Report createdReport = null;

		createdReport = repoService.addReport(report, authKey);

		return new ResponseEntity<Report>(createdReport, HttpStatus.CREATED);
	}

	/*
	 * Delete a report
	 */
	@DeleteMapping("/removeReport")
	public ResponseEntity<Report> deleteTicket(@RequestParam Integer reportId, @RequestParam String authKey) throws ReportException {
		Report report = null;

		report = repoService.deleteReport(reportId, authKey);

		return new ResponseEntity<Report>(report, HttpStatus.OK);
	}
}
