package com.springboot.demo.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.controller.namespace.ApiNamespace;
import com.springboot.demo.entity.User;
import com.springboot.demo.exception.DatabaseException;
import com.springboot.demo.exception.EntityNotFoundException;
import com.springboot.demo.exception.IllegalVariableException;
import com.springboot.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = ApiNamespace.URI_USERS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("user create / update / delete / get / list")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@Validated @RequestBody User user) throws DatabaseException {
		user.setId(null);
		return userService.create(user);
	}
	
	@GetMapping
	public List<User> list(
			@RequestParam(required = false) String name, 
			@RequestParam(required = false, defaultValue = "0") int offset, 
			@RequestParam(required = false, defaultValue = "100") int limit, 
			@RequestParam(required = false) String orderBy, 
			@RequestParam(required = false, defaultValue = "true") boolean ascending){
		return Collections.emptyList();
	}
	
	@ApiOperation(value = "Get an user by id", notes = "ahaha~~~, you can put some specific notes here for this API")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the entity"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Unknown error")
	})
	@GetMapping("/{id}")
	public User get(@PathVariable Long id) throws DatabaseException, EntityNotFoundException {
		return userService.get(id);
	}
	
	@PutMapping("/{id}")
	public User update(@PathVariable Long id, @RequestBody User user) throws DatabaseException, IllegalVariableException {
		if(user.getId()==null || !user.getId().equals(id)) {
			logger.warn(" === user.id and path.id are not matched, {}, {}", id, user.getId());
			throw new IllegalVariableException("ID_MISMATCHED", "path.id and user.id are not matched!");
		}
		return userService.update(user);
	}
	
	@PatchMapping("/{id}")
	public User updatePatch(@PathVariable Long id, @RequestParam(required=false) String name, @RequestParam(required=false) String email) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws DatabaseException {
		userService.delete(id);
	}
}
