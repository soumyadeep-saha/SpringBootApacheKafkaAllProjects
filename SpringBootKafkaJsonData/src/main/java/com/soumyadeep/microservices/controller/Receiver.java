package com.soumyadeep.microservices.controller;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.soumyadeep.microservices.model.Car;

@Service
public class Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	@Value("${kafka.topic.json}")
	private String jsonTopic;

	/*
	 * For testing convenience, we added a CountDownLatch. This allows the POJO to
	 * signal that a message is received. This is something you are not likely to
	 * implement in a production application.
	 */
	CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@KafkaListener(topics = "${kafka.topic.json}", groupId = "json")
	public void publish(Car car) {
		LOGGER.info("Consumer receiving car='{}'", car.toString());
		latch.countDown();
	}
}
