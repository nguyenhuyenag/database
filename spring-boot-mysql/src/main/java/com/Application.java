package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.service.EntityManagerService;
import com.service.VocabService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	EntityManagerService emService;
	
	@Autowired
	VocabService vbService;

	@Override
	public void run(String... args) throws Exception {
		// service.insert();
		// service.findOne();
		// service.findAllByQuery();
		// service.findAllByTypedQuery();
		// vbService.CollectionParam();
		vbService.findUserByEmails();
	}

}
