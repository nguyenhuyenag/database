package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.repository.VocabRepository;
import com.service.EntityManagerService;
import com.service.ProcedureService;
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

	@Autowired
	VocabRepository repository;

	@Autowired
	ProcedureService proceduce;

	Logger logger = LoggerFactory.getLogger(Application.class);

	@Override
	public void run(String... args) throws Exception {
		// service.insert();
		// service.findOne();
		// service.findAllByQuery();
		// service.findAllByTypedQuery();
		// vbService.CollectionParam();
		// vbService.findUserByEmails();
		// epository.findAll().stream().limit(10).forEach(t->System.out.println(t));
		// repository.updateLastModified("actual");
		// proceduce.callProcedureByQuery().forEach(t -> System.out.println(t));
		proceduce.callProcedureWithParam();
	}

}
