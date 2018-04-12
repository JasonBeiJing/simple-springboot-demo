package com.springboot.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
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

import com.springboot.demo.entity.Attribute;
import com.springboot.demo.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {

	private static AtomicInteger count = new AtomicInteger();

	private static AtomicLong ID = new AtomicLong();

	@PostMapping
	public User create(@RequestBody User user) {
		return getUser(ID.incrementAndGet());
	}

	@GetMapping
	public List<User> list(
			@RequestParam(required = false) List<Long> ids,
			@RequestParam(required = false, defaultValue = "name") String orderBy, 
			@RequestParam(required = false, defaultValue = "true") boolean ascending, 
			@RequestParam(required = false, defaultValue = "0") int offset, 
			@RequestParam(required = false, defaultValue = "100") int limit) {
		
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		List<User> users = new ArrayList<>(ids.size());
		for (long i = 0; i < ids.size(); i++) {
			users.add(getUser(i));
		}
		return users;
	}

	@GetMapping("/{id}")
	public User get(@PathVariable Long id) {
		try {
			int timeout = ThreadLocalRandom.current().nextInt(0, 5);
			System.err.println("========= 耗时 : " + timeout + "s =========" + count.incrementAndGet());
			TimeUnit.SECONDS.sleep(timeout);
		} catch (Exception e) {
		}
		
		return getUser(id);
	}

	@PutMapping("/{id}")
	public User update(@PathVariable Long id, @RequestBody User user) {
		return user;
	}

	@PatchMapping("/{id}")
	public User updateUserName(@PathVariable Long id, @RequestParam String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {

	}
	
	private User getUser(Long id) {
		User user = new User();
		user.setId(id);
		user.setName("SHUJIE-" + id);
		List<Attribute> attributes = new ArrayList<>();
		attributes.add(new Attribute("x", "a string value"));
		attributes.add(new Attribute("y", LocalDateTime.now()));
		Map<String, String> maps = new HashMap<>();
		maps.put("hi", "zhangsan");
		maps.put("hello", "lisi");
		attributes.add(new Attribute("z", maps));
		user.setAttributes(attributes);
		return user;
	}
}
