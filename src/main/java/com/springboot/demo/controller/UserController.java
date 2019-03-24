package com.springboot.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import com.springboot.demo.entity.Attribute;
import com.springboot.demo.entity.User;
import com.springboot.demo.exception.DatabaseException;
import com.springboot.demo.exception.EntityNotFoundException;
import com.springboot.demo.exception.IllegalVariableException;
import com.springboot.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = ApiNamespace.URI_USERS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "用户APIs", tags= {"A"})
@ApiResponses(value = {
		@ApiResponse(code = 500, message = "服务端错误，请联系API管理员")
})
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "创建注册用户")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "成功注册用户"),
			@ApiResponse(code = 400, message = "输入参数有误")
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@Validated @RequestBody User user) throws DatabaseException, IllegalVariableException {
		user.setId(null);
		return userService.create(user);
	}
	
	@ApiOperation(value = "获取用户列表信息")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "成功获取到用户列表信息"),
	        @ApiResponse(code = 401, message = "用户未登录"),
	        @ApiResponse(code = 403, message = "用户未授权")
	})
	@GetMapping
	public List<User> list(
			@RequestParam(required = false) @ApiParam("用户名字，支持模糊查询") String name,
			@RequestParam(required = false) @ApiParam("用户手机号") String phone,
			@RequestParam(required = false) @DateTimeFormat(iso=ISO.DATE_TIME, pattern="yyyy-MM-dd HH:mm:ss") @ApiParam("用户注册日期起点") LocalDateTime from,
			@RequestParam(required = false) @DateTimeFormat(iso=ISO.DATE_TIME,  pattern="yyyy-MM-dd HH:mm:ss") @ApiParam("用户注册日期终点") LocalDateTime to,
			@RequestParam @ApiParam(value = "分页参数：起始位置", required=true) int offset, 
			@RequestParam @ApiParam(value = "分页参数：每页多少条", required=true) int limit, 
			@RequestParam(required = false, defaultValue="name") @ApiParam("按照什么字段进行排序") String orderBy, 
			@RequestParam(required = false, defaultValue = "true") @ApiParam("如果排序，正序还是反序") boolean ascending){
		List<User> out = new ArrayList<>();
		User u1 = new User(1L, "x", "111");
		List<Attribute> attributes1 = new ArrayList<>();
		attributes1.add(new Attribute("x", "z"));
		u1.setAttributes(attributes1);
		out.add(u1);
		User u2 = new User(2L, "y", "2222");
		List<Attribute> attributes2 = new ArrayList<>();
		attributes2.add(new Attribute("1", "2"));
		u2.setAttributes(attributes2);
		out.add(u2);
		return out;
	}
	
	@ApiOperation(value = "根据ID获取用户信息", notes = "这里我们可以添加更多API相关的描述,比如该API即将在xxxx年yy月xx日过期下线，请及时切换到新的API:/a/b")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "根据ID成功获取到用户信息"),
	        @ApiResponse(code = 401, message = "用户未登录"),
	        @ApiResponse(code = 403, message = "用户未授权"),
	        @ApiResponse(code = 404, message = "根据ID未找到到用户资源信息")
	})
	@GetMapping("/{id}")
	public User get(@PathVariable Long id) throws DatabaseException, EntityNotFoundException {
		return userService.get(id);
	}
	
	@ApiOperation("根据用户ID更新用户信息")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "更行用户信息成功"),
	        @ApiResponse(code = 401, message = "用户未登录"),
	        @ApiResponse(code = 403, message = "用户未授权"),
	        @ApiResponse(code = 404, message = "根据ID未找到到用户资源信息")
	})
	@PutMapping("/{id}")
	public User update(@PathVariable Long id, @Validated @RequestBody User user) throws DatabaseException, IllegalVariableException {
		if(user.getId()==null || !user.getId().equals(id)) {
			logger.warn(" === user.id and path.id are not matched, {}, {}", id, user.getId());
			throw new IllegalVariableException(IllegalVariableException.ERROR_CODE.ID_MISMATCHED, "path.id and user.id are not matched!");
		}
		return userService.update(user);
	}
	
	@ApiOperation("根据用户ID更新用户部分信息")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "更行用户部分信息成功"),
	        @ApiResponse(code = 401, message = "用户未登录"),
	        @ApiResponse(code = 403, message = "用户未授权"),
	        @ApiResponse(code = 404, message = "根据ID未找到到用户资源信息")
	})
	@PatchMapping("/{id}")
	public User updatePatch(@PathVariable Long id, @RequestParam String name) throws DatabaseException, EntityNotFoundException {
		User u = get(id);
		u.setName(name);
		return userService.update(u);
	}
	
	@ApiOperation("根据用户ID删除用户")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "成功删除用户"),
	        @ApiResponse(code = 401, message = "用户未登录"),
	        @ApiResponse(code = 403, message = "用户未授权")
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws DatabaseException {
		userService.delete(id);
	}
}
