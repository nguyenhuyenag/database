package com.service.impl;

import java.util.List;

import com.entity.Vocabulary;
import com.request.InsertDTO;

public interface TemplateService {
	
	Vocabulary findOne(String word);
	
	Vocabulary insert(InsertDTO dto);
	
	boolean deleteOne(String word);
	
	Vocabulary update(InsertDTO dto);
	
	List<Vocabulary> findAll();
	
	List<Vocabulary> findAllAndSort();
	
}
