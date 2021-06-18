package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Vocabulary;
import com.service.VocabService;

@RestController
public class VocabController {

	@Autowired
	private VocabService service;

	@GetMapping("find-all")
	private ResponseEntity<?> findAll() {
		List<Vocabulary> list = service.findAll();
		return ResponseEntity.ok(list);
	}

}
