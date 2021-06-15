package com.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.request.InsertDTO;

@Service
public class TemplateServiceImpl implements TemplateService {

	private static final String WORD = "word";
	private static final String COUNT = "count";
	private static final String VOCABULARY = "vocabulary";

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean isExists(String word) {
		Query query = new Query(Criteria.where(WORD).is(word.toLowerCase()));
		// mongoTemplate.exists(query, VOCABULARY);
		return mongoTemplate.exists(query, Vocabulary.class);
	}

	@Override
	public Vocabulary findOne(String word) {
		Query query = new Query(Criteria.where(WORD).is(word));
		mongoTemplate.findOne(query, Vocabulary.class);
		return mongoTemplate.findOne(query, Vocabulary.class);
	}

	@Override
	public Vocabulary insert(InsertDTO dto) {
		// is exist
		if (isExists(dto.getWord())) {
			return null;
		}
		Vocabulary entity = new Vocabulary(dto.getWord(), dto.getPronounce(), dto.getTranslate());
		// insert(objectToSave)
		// insert(objectToSave, collectionName)
		return mongoTemplate.insert(entity, VOCABULARY);
	}

	@Override
	public Vocabulary update(InsertDTO dto) {
		Vocabulary v = findOne(dto.getWord().toLowerCase());
		// not found
		if (v == null) {
			return null;
		}
		v.setPronounce(dto.getPronounce());
		v.setTranslate(dto.getTranslate());
		/**
		 * save(): Update the whole object, if '_id' is present, perform an update, else
		 * insert it
		 */
		return mongoTemplate.save(v);
	}

	@Override
	public boolean remove(String word) {
		Vocabulary v = findOne(word);
		// is exist
		if (v != null) {
			mongoTemplate.remove(v, VOCABULARY);
			return true;
		}
		return false;
	}

	@Override
	public List<Vocabulary> findAll() {
		return mongoTemplate.findAll(Vocabulary.class);
	}

	@Override
	public List<Vocabulary> findAllAndSort() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "count"));
		List<Vocabulary> list = mongoTemplate.find(query, Vocabulary.class);
		return list.stream().limit(20).collect(Collectors.toList());
	}

	@Override
	public Vocabulary findAndModify(InsertDTO dto) {
		Query query = new Query(Criteria.where(WORD).is(dto.getWord()));
		Update update = new Update();
		update.set(COUNT, dto.getCount());
		// findAndModify(query, update, Vocabulary.class);
		return mongoTemplate.findAndModify(query, update, Vocabulary.class, VOCABULARY);
	}

}
