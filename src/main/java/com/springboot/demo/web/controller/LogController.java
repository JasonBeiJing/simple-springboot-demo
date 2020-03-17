package com.springboot.demo.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.domain.entity.Log;
import com.springboot.demo.domain.exception.EntityNotFoundException;
import com.springboot.demo.service.LogService;
import com.springboot.demo.web.namespace.ApiNamespace;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = ApiNamespace.URI_LOGS, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "日志APIs", tags = { "B" })
@ApiResponses(value = { 
		@ApiResponse(code = 400, message = "客户端输入参数有误"), 
		@ApiResponse(code = 401, message = "用户未登录"),
		@ApiResponse(code = 403, message = "用户未授权"), 
		@ApiResponse(code = 500, message = "服务端错误，请联系API管理员") })
public class LogController {

	@Autowired
	private LogService logService;

	@ApiOperation(value = "创建日志")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "成功创建日志") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Log create(@RequestBody @Validated Log log) {
		log.setId(null);
		return logService.createLog(log);
	}

	@ApiOperation(value = "获取日志列表")
	@ApiResponses(@ApiResponse(code = 200, message = "成功获取到日志列表信息"))
	@GetMapping
	public List<Log> list(
			@RequestParam(required = false) List<String> keys, 
			@RequestParam(required = false) String type, 
			@RequestParam(required = false) String name, 
			@RequestParam(required = false) LocalDateTime from, 
			@RequestParam(required = false) LocalDateTime to,
			@RequestParam(required = false, defaultValue = "1") int curPage, 
			@RequestParam(required = false, defaultValue = "100") int pageSize) {
		return logService.queryLogs(keys, type, name, curPage, pageSize);
	}

	@ApiOperation(value = "根据ID获取日志详情")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "成功获取到日志信息"),
			@ApiResponse(code = 404, message = "日志不存在") })
	@GetMapping("/{id}")
	public Log get(@PathVariable String id) throws EntityNotFoundException {
		return logService.getLogById(id);
	}
	
	@ApiOperation(value = "根据ID删除")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "成功获取到日志信息") })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) throws EntityNotFoundException {
		logService.deleteLogById(id);
	}
}
