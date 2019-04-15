package com.springboot.demo.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import com.springboot.demo.entity.Tag;

public class TagRepositoryImpl implements ITagRepository {
	
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public List<Tag> findAllByArguments(Collection<String> keys, Collection<String> types, String name) {
		Query query = new Query();
		if(!CollectionUtils.isEmpty(keys)) {
			query.addCriteria(Criteria.where("keys").in(keys));
		}
		
		return mongoOperations.find(query, Tag.class);
	}
}
