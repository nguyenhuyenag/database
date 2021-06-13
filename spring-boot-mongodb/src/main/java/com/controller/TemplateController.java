package com.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Vocabulary;
import com.request.InsertDTO;
import com.service.impl.TemplateService;

@RestController
@RequestMapping("template")
public class TemplateController {

	@Autowired
	private TemplateService service;

	@GetMapping("find-one")
	public ResponseEntity<?> findOne(String word) {
		Vocabulary v = service.findOne(word);
		if (v != null) {
			return ResponseEntity.ok(v);
		}
		return ResponseEntity.ok(Arrays.asList("Not found!"));
	}

	@PostMapping("insert")
	public ResponseEntity<?> insert(@RequestBody InsertDTO dto) {
		Vocabulary v = service.insert(dto);
		if (v != null) {
			return ResponseEntity.ok(v);
		}
		return ResponseEntity.ok(Arrays.asList(dto.getWord() + " already existed!"));
	}

	@PostMapping("update")
	public ResponseEntity<?> update(@RequestBody InsertDTO dto) {
		Vocabulary v = service.update(dto);
		if (v != null) {
			return ResponseEntity.ok(v);
		}
		return ResponseEntity.ok(Arrays.asList(dto.getWord() + " doesn't existed!"));
	}

	@GetMapping("delete-one")
	public ResponseEntity<?> deleteOne(String word) {
		boolean b = service.deleteOne(word);
		System.out.println(b);
		return null;
	}

	@GetMapping("find-all")
	public ResponseEntity<?> findAll() {
		List<?> list = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("find-all-and-sort")
	public ResponseEntity<?> findAllAndSort() {
		List<?> list = service.findAllAndSort();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

}
