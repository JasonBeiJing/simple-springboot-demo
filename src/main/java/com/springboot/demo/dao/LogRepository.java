package com.springboot.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.demo.dao.impl.ILogRepository;
import com.springboot.demo.domain.entity.Log;

public interface LogRepository extends CrudRepository<Log, String>, ILogRepository{

	Log findByKey(String key);
}
