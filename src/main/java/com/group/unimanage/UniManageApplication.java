package com.group.unimanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class UniManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniManageApplication.class, args);
	}
}
