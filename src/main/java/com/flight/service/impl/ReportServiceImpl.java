package com.flight.service.impl;

import java.util.List;
import java.util.Optional;

import com.flight.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.exception.ReportException;
import com.flight.model.CurrentUserLoginSession;
import com.flight.model.Report;
import com.flight.repository.PackageRepository;
import com.flight.repository.ReportRepository;
import com.flight.repository.SessionRepository;
import com.flight.repository.UserRepository;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	PackageRepository pkgRepo;
	
	@Autowired
	SessionRepository sessionRepo;
	
	@Autowired
	ReportRepository reportRepo;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public Report addReport(Report report, String authKey) throws ReportException {
		Optional<CurrentUserLoginSession> curUser = sessionRepo.findByAuthkey(authKey);
		String userType = userRepo.findById(curUser.get().getUserId()).get().getUserType();
		if(userType.equalsIgnoreCase("user")) {
			throw new ReportException("Unauthorized Request...");
		}
		else {
			return reportRepo.save(report);
		}
	}

	@Override
	public Report deleteReport(Integer reportId, String authKey) throws ReportException {
		Optional<CurrentUserLoginSession> curUser = sessionRepo.findByAuthkey(authKey);
		String userType = userRepo.findById(curUser.get().getUserId()).get().getUserType();
		if(userType.equalsIgnoreCase("user")) {
			throw new ReportException("Unauthorized Request...");
		}
		Optional<Report> reportOpt =  reportRepo.findById(reportId);
		if(!reportOpt.isPresent()) {
			throw new ReportException("Report not exist...");
		}
		reportRepo.deleteById(reportId);
		return reportOpt.get();
	}
	

	@Override
	public List<Report> viewAllReports(String authKey) throws ReportException {
		Optional<CurrentUserLoginSession> curUser = sessionRepo.findByAuthkey(authKey);
		String userType = userRepo.findById(curUser.get().getUserId()).get().getUserType();
		if(userType.equalsIgnoreCase("user")) {
			throw new ReportException("Unauthorized Request...");
		}
		else {
			return reportRepo.findAll();
		}
	
	}

}
