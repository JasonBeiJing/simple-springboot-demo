package com.springboot.demo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.domain.entity.Log;
import com.springboot.demo.domain.exception.EntityNotFoundException;
import com.springboot.demo.service.LoggingService;
import com.springboot.demo.web.namespace.ApiNamespace;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = ApiNamespace.URI_LOGS, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "日志APIs", tags= {"B"})
public class LoggingController {
	
	@Autowired
	private LoggingService logService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Log create(@RequestBody Log log) {
		log.setId(null);
		return logService.createLog(log);
	}
	
	@GetMapping
	public List<Log> list(){
		return logService.listLogs(null, null, null);
	}

	@GetMapping("/{id}")
	public Log get(@PathVariable String id) throws EntityNotFoundException {
		return logService.getLogById(id);
	}
}
