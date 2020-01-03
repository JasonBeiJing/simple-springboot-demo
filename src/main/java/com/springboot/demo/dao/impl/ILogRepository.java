package com.springboot.demo.dao.impl;

import java.util.Collection;
import java.util.List;

import com.springboot.demo.domain.entity.Log;

public interface ILogRepository {
	List<Log> findAllByArguments(Collection<String> keys, Collection<String> types, String name);
}
