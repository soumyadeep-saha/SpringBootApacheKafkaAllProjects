package com.soumyadeep.microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerController {
	
	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerController.class);
	
	@KafkaListener(topics="soumyadeeptopic", groupId="group-id1")
	public void consume(String message) {
		logger.info("Consumed message: {}", message);
	}

}
