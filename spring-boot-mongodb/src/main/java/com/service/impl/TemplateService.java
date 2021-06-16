package com.service.impl;

import java.util.List;

import org.bson.Document;

import com.entity.Vocabulary;
import com.request.InsertDTO;

public interface TemplateService {

	boolean isExists(String word);

	boolean remove(String word);

	boolean collectionExists();

	Vocabulary insert(InsertDTO dto);

	Vocabulary findOne(String word);

	// giong voi phuong thuc `abc` nhung tra ve object truoc khi sua doi
	Vocabulary findAndModify(InsertDTO dto);

	Vocabulary update(InsertDTO dto);

	List<Vocabulary> findAll();

	List<Vocabulary> findAllAndSort();

	// BSON & Document

	List<Vocabulary> basicQuery(); // <- MongoDB `raw` query

	Document insertAny(String jsonString);

	Document bsonFilter();

}
