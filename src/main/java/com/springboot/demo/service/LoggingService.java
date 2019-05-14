package com.springboot.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.dao.LogRepository;
import com.springboot.demo.entity.Log;
import com.springboot.demo.exception.EntityNotFoundException;

@Service
public class LoggingService {

	@Autowired
	private LogRepository logRepository;

	public Log createLog(Log log) {
		return logRepository.save(log);
	}

	public Log getLogById(String id) throws EntityNotFoundException {
		Optional<Log> tag = logRepository.findById(id);
		if(!tag.isPresent()) {
			throw new EntityNotFoundException(EntityNotFoundException.ERROR_CODE.TAG_NOT_FOUND, id);
		}
		return tag.get();
	}
	
	public Log getLogByKey(String key) throws EntityNotFoundException {
		Log tag = logRepository.findByKey(key);
		if(Objects.isNull(tag)) {
			throw new EntityNotFoundException(EntityNotFoundException.ERROR_CODE.TAG_NOT_FOUND, key);
		}
		return tag;
	}
	
	public List<Log> listLogs(List<String> keys, List<String> types, String name){
		return logRepository.findAllByArguments(keys, types, name);
	}
}
