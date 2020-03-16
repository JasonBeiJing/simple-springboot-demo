package com.springboot.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.dao.LogRepository;
import com.springboot.demo.domain.entity.Log;
import com.springboot.demo.domain.exception.EntityNotFoundException;
import com.springboot.demo.util.MyAspectAnnotation;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;

	public Log createLog(Log log) {
		log.setId(null);
		log.setCreated(LocalDateTime.now());
		log.setCreatedBy("whom");
		return logRepository.save(log);
	}

	@MyAspectAnnotation
	public Log getLogById(String id) throws EntityNotFoundException {
		Log result = null;
		Optional<Log> log = logRepository.findById(id);
		if(!log.isPresent() || Objects.nonNull((result = log.get()).getDeleted())) {
			throw new EntityNotFoundException(EntityNotFoundException.ERROR_CODE.LOG_NOT_FOUND, id);
		}
		return result;
	}
	
	public void deleteLogById(String id) {
		Optional<Log> log = logRepository.findById(id);
		if(log.isPresent()) {		
			Log exitedLog = log.get();
			exitedLog.setDeleted("whom");
			exitedLog.setDeletedBy(LocalDateTime.now());
			logRepository.save(exitedLog);
		} 
	}
	
	public List<Log> queryLogs(List<String> keys, String type, String name, int curPage, int pageSize) {
		return logRepository.findAllByArguments(keys, type, name, curPage, pageSize);
	}
}
