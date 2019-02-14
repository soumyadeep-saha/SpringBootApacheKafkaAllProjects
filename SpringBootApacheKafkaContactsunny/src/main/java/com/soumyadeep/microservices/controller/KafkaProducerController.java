package com.soumyadeep.microservices.controller;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {

	@Value("${kafka.topic.thetechcheck}")
	private String theTechCheckTopicName;

	@Value("${kafka.bootstrap.servers}")
	private String kafkaBootstrapServers;

	private final static Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);

	public KafkaProducer<String, String> producerProperties() {

		/*
		 * Defining producer properties.
		 */
		Properties producerProperties = new Properties();
		producerProperties.put("bootstrap.servers", kafkaBootstrapServers);
		producerProperties.put("acks", "all");
		producerProperties.put("retries", 0);
		producerProperties.put("batch.size", 16384);
		producerProperties.put("linger.ms", 1);
		producerProperties.put("buffer.memory", 33554432);
		producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		/*
		 * Creating a Kafka Producer object with the configuration above.
		 */
		KafkaProducer<String, String> producer = new KafkaProducer<>(producerProperties);

		return producer;
	}

	private static void sendKafkaMessage(String payload, KafkaProducer<String, String> producer, String topic) {
		logger.info("Sending Kafka message: " + payload);
		producer.send(new ProducerRecord<>(topic, payload));
	}

	@RequestMapping("/kafka/publishString")
	public String publishString() {
		/*
		 * Creating a loop which iterates 10 times, from 0 to 9, and sending a simple
		 * message to Kafka.
		 */
		for (int index = 0; index < 10; index++) {
			sendKafkaMessage("The index is now: " + index, producerProperties(), theTechCheckTopicName);
		}

		return "Message published successfully";
	}
	
	@RequestMapping("/kafka/publishJson")
	public String publishJson() {
		/*
		 * Creating a loop which iterates 10 times, from 0 to 9, and sending a simple
		 * message to Kafka.
		 */
		for (int index = 0; index < 10; index++) {
			
			JSONObject jsonObject = new JSONObject();
			JSONObject nestedJsonObject = new JSONObject();
			
			try {
				jsonObject.put("index", index);
				jsonObject.put("message", "The index is now at"+index);
				nestedJsonObject.put("nestedObjct", "This is a nested JSON object with index: " + index);
				jsonObject.put("nestedJsonObject", nestedJsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			sendKafkaMessage(jsonObject.toString(), producerProperties(), theTechCheckTopicName);
		}
		return "Message published successfully";
	}
}
