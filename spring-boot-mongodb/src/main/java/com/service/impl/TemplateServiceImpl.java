package com.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.request.InsertDTO;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	private MongoTemplate template;

	@Override
	public Vocabulary findOne(String word) {
		Query query = new Query(Criteria.where("word").is(word));
		template.findOne(query, Vocabulary.class);
		return template.findOne(query, Vocabulary.class);
	}

	@Override
	public Vocabulary insert(InsertDTO dto) {
		Vocabulary v = findOne(dto.getWord());
		// is exist
		if (v != null) {
			return null;
		}
		Vocabulary entity = new Vocabulary(dto.getWord(), dto.getPronounce(), dto.getTranslate());
		/**
		 * save(): Update the whole object, if '_id' is present, perform an update, else
		 * insert it, that is an 'upsert'
		 */
		return template.save(entity);
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
		return template.save(v);
	}

	@Override
	public boolean deleteOne(String word) {
		Vocabulary v = findOne(word);
		// is exist
		if (v != null) {
			template.remove(v);
			return true;
		}
		return false;
	}

	@Override
	public List<Vocabulary> findAll() {
		return template.findAll(Vocabulary.class);
	}

	@Override
	public List<Vocabulary> findAllAndSort() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "count"));
		List<Vocabulary> list = template.find(query, Vocabulary.class);
		return list.stream().limit(20).collect(Collectors.toList());
	}

}
