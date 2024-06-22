package com.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{
	
}
