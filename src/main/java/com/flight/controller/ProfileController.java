package com.flight.controller;

import com.flight.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/profile")
public class ProfileController {

	private final MessageProducer messageProducer;

	@Autowired
	public ProfileController(MessageProducer messageProducer) {
		this.messageProducer = messageProducer;
	}

	@PostMapping("/send")
	public ResponseEntity<String> publishToTopic(@RequestParam String message) {
		messageProducer.sendMessage("my-topic", message);
		return ResponseEntity.ok("Message sent: " + message);
	}


}
