package com.flight.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

	@GetMapping("/health")
	public ResponseEntity<Map<String, String>> ping() {
		Map<String, String> status = new HashMap<>();
		status.put("status", "UP");
		return ResponseEntity.ok(status);
	}
}
