package com.service.impl;

import java.util.List;

import com.entity.Vocabulary;
import com.request.InsertDTO;

public interface TemplateService {
	
	boolean isExists(String word);

	boolean remove(String word);
	
	Vocabulary findOne(String word);

	Vocabulary insert(InsertDTO dto);
	
	// giong voi phuong thuc `abc` nhung tra ve object truoc khi sua doi
	Vocabulary findAndModify(InsertDTO dto);

	Vocabulary update(InsertDTO dto);

	List<Vocabulary> findAll();

	List<Vocabulary> findAllAndSort();

}
