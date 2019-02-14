package com.soumyadeep.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soumyadeep.springboot.service.KafkaSender;

@RestController
public class kafkaController {
	
	@Autowired
	private KafkaSender kafkaSender;
	
	@RequestMapping("/kafka/producer")
	public String producer(@RequestParam(name="message") String message) {
		
		kafkaSender.send(message);
		
		return "Message sent to the Kafka Topic soumyadeep_topic Successfully";
	}
}
