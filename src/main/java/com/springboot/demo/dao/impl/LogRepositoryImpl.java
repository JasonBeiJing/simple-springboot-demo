package com.springboot.demo.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import com.springboot.demo.domain.entity.Log;

public class LogRepositoryImpl implements ILogRepository {
	
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public List<Log> findAllByArguments(Collection<String> keys, String type, String name, int curPage, int pageSize) {
		Query query = Query.query(Criteria.where("deleted").exists(false));
		if(!CollectionUtils.isEmpty(keys)) {
			query.addCriteria(Criteria.where("keys").in(keys));
		}
		// zero-based page index.
		final int index = curPage - 1;
		// the size of the page to be returned.
		final int size = pageSize;
		query.with(PageRequest.of(index, size, Sort.by(Direction.DESC, "created")));
		return mongoOperations.find(query, Log.class);
	}
}
