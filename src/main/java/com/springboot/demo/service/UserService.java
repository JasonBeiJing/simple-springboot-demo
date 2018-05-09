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
		user.setAttributes(otherBusiness());
		return user;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public User create(User user) {
		if(user.getId()!=null) {
			user.setId(null);
		}
		User result = userDao.save(user);
		result.setAttributes(otherBusiness());
		return result;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public User update(User user) {
		if(user.getId()==null) {
			throw new IllegalArgumentException("ID_REQUIRED");
		}
		User result = userDao.save(user);
		result.setAttributes(otherBusiness());
		return result;
	}
	
	private List<Attribute> otherBusiness() {
		List<Attribute> myBusinessAttributes = new ArrayList<>();
		myBusinessAttributes.add(new Attribute("1", "a"));
		myBusinessAttributes.add(new Attribute("2", "b"));
		
		return myBusinessAttributes;
	}

}
