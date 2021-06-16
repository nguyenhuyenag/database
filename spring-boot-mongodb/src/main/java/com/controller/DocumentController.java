package com.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.impl.TemplateService;

@RestController
@RequestMapping("document")
public class DocumentController {

	@Autowired
	private TemplateService service;

	// Co the dung
	// @RequestBody Document jsonString
	@PostMapping("insert-any")
	public ResponseEntity<?> insertAny(@RequestBody String jsonString) {
		Document d = service.insertAny(jsonString);
		return ResponseEntity.ok(d);
	}

}
