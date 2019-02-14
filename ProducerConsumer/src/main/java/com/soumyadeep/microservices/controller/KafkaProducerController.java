package com.soumyadeep.microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);
	
	public static final String TOPIC = "soumyadeeptopic";
	
	@RequestMapping("/kafka/publish")
	public String publish(@RequestParam(name="message") String message) {
		kafkaTemplate.send(TOPIC, message);
		logger.info("Produced message: {}", message);
		return "Message published successfully";
	}
}
