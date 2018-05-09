package com.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.entity.User;
import com.springboot.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public User create(@RequestBody User user) {
		user.setId(null);
		return userService.create(user);
	}
	
	@GetMapping("/{id}")
	public User get(@PathVariable Long id) {
		return userService.get(id);
	}
	
	@PutMapping("/{id}")
	public User update(@PathVariable Long id, @RequestBody User user) {
		if(user.getId()==null || !user.getId().equals(id)) {
			throw new IllegalArgumentException("ID_MISMATCHED");
		}
		return userService.update(user);
	}
}
