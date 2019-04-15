package com.springboot.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.demo.dao.impl.ITagRepository;
import com.springboot.demo.entity.Tag;

public interface TagRepository extends CrudRepository<Tag, String>, ITagRepository{

	Tag findByKey(String key);
}
