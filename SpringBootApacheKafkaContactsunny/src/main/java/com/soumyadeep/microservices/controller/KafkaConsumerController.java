package com.soumyadeep.microservices.controller;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerController.class);
    
    @Value("${zookeeper.groupId}")
    private String zookeeperGroupId;
    
	@Value("${kafka.topic.thetechcheck}")
	private String theTechCheckTopicName;

	@Value("${kafka.bootstrap.servers}")
	private String kafkaBootstrapServers;

   
    public void producerProperties() {
    
    	/*
    	 * Defining Kafka consumer properties.
    	 */
    	Properties consumerProperties = new Properties();
    	consumerProperties.put("bootstrap.servers", kafkaBootstrapServers);
    	consumerProperties.put("group.id", zookeeperGroupId);
    	consumerProperties.put("zookeeper.session.timeout.ms", "6000");
    	consumerProperties.put("zookeeper.sync.time.ms","2000");
    	consumerProperties.put("auto.commit.enable", "false");
    	consumerProperties.put("auto.commit.interval.ms", "1000");
    	consumerProperties.put("consumer.timeout.ms", "-1");
    	consumerProperties.put("max.poll.records", "1");
    	consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    	consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    	
    	/*
    	 * Creating a thread to listen to the kafka topic
    	 */
    	Thread kafkaConsumerThread = new Thread(() -> {
    	    logger.info("Starting Kafka consumer thread.");

    	    SimpleKafkaConsumer simpleKafkaConsumer = new SimpleKafkaConsumer(
    	            theTechCheckTopicName,
    	            consumerProperties
    	    );

    	    simpleKafkaConsumer.runSingleWorker();
    	});

    	/*
    	 * Starting the first thread.
    	 */
    	kafkaConsumerThread.start();
    }
}

