package com.soumyadeep.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootSchedulingAllTypesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSchedulingAllTypesApplication.class, args);
	}

}

