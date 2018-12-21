package com.springboot.demo.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.demo.Application;
import com.springboot.demo.dao.UserDao;
import com.springboot.demo.entity.User;
import com.springboot.demo.exception.DatabaseException;
import com.springboot.demo.exception.EntityNotFoundException;
import com.springboot.demo.service.UserService;

//not good, has to start up the application

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes={Application.class})
public class UserServiceTest {

	
	@MockBean
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testCreate() throws DatabaseException, EntityNotFoundException {
		User user = new User();
		user.setName("xxx");
		Mockito.when(userDao.save(user)).thenReturn(user);
		userService.create(user);
		
		//your Assert
	}
	
	@Test
	public void testGet() throws DatabaseException, EntityNotFoundException {
		User user = new User();
		user.setId(666L);
		Mockito.when(userDao.get(666L)).thenReturn(user);
		userService.get(666L);
		
		//your Assert
	}
	
	
}
