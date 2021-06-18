package com.service;

import java.util.List;

import com.entity.Vocabulary;

public interface VocabService {

	List<Vocabulary> findAll();
	
	void insert();
	
	void findOne();

//	List<Vocabulary> incompleteVocabulary();
//	
//	Vocabulary getRandomVocab(String flag);
//
//	Vocabulary getRandomVocab2();
//
//	void increaseCountById(Vocabulary vocab);
//	
//	List<String> incomplete();
//	
//	Vocabulary search(String word);
//
//	
//	
//	Page<Vocabulary> pagination(int page, int pageSize);
//	
//	Page<Vocabulary> searchByWord(String word, int page);
//	
//	void deleteByWord(String word);
//	
//	Vocabulary update(Vocabulary v);
//	
//	String pluralNoun(String noun);

}
