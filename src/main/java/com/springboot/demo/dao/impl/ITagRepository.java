package com.springboot.demo.dao.impl;

import java.util.Collection;
import java.util.List;

import com.springboot.demo.entity.Tag;

public interface ITagRepository {
	List<Tag> findAllByArguments(Collection<String> keys, Collection<String> types, String name);
}
