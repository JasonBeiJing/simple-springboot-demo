package com.springboot.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springboot.demo.dao.impl.ILogRepository;
import com.springboot.demo.domain.entity.Log;

public interface LogRepository extends CrudRepository<Log, String>, ILogRepository{

	Optional<Log> findByKey(String key);
}
