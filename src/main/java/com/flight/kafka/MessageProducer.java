package com.flight.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String topic, String message) {
		var response = kafkaTemplate.send(topic, message);
		System.out.println("Response: " + response);
	}

}