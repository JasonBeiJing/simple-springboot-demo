package com.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.demo.db.UserDao;
import com.springboot.demo.entity.Attribute;
import com.springboot.demo.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	
	public User get(Long id) {
		User user = userDao.get(id);
		
		List<Attribute> myBusinessAttributes = new ArrayList<>();
		myBusinessAttributes.add(new Attribute("1", "a"));
		myBusinessAttributes.add(new Attribute("2", "b"));
		user.setAttributes(myBusinessAttributes);
		
		return user;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public User create(User user) {
		if(user.getId()!=null) {
			
		}
		userDao.save(user);
		return null;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public User update(User user) {
		if(user.getId()==null) {
			
		}
		userDao.save(user);
		return null;
	}

}
