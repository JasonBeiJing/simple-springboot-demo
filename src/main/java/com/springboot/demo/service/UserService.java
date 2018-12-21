package com.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.demo.dao.UserDao;
import com.springboot.demo.entity.Attribute;
import com.springboot.demo.entity.User;
import com.springboot.demo.exception.DatabaseException;
import com.springboot.demo.exception.EntityNotFoundException;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private static final String CACHE_NAME = "USER_DAO_CACHE_";
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate<String, User> userRedisTemplate;
	
	public User get(Long id) throws DatabaseException, EntityNotFoundException {
		User user = userRedisTemplate.opsForValue().get(CACHE_NAME + id);
		if(logger.isDebugEnabled()) {			
			logger.debug(" === got user from cache ? {} ==== ", user == null ? "NO" : "YES");
		}
		if(user == null) {
			userRedisTemplate.opsForValue().set(CACHE_NAME + id, user = userDao.get(id), 30, TimeUnit.SECONDS);
		}
		if(user == null) {
			logger.warn("user not found with id: {}", id);
			throw new EntityNotFoundException(EntityNotFoundException.ERROR_CODE.USER_NOT_FOUND, id);
		}
		user.setAttributes(otherBusiness());
		return user;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class) //rollbackFor 默认为：RuntimeException OR Error
	public User create(User user) throws DatabaseException {
		if(user.getId()!=null) {
			user.setId(null);
		}
		User result = userDao.save(user);
		result.setAttributes(otherBusiness());
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class) //rollbackFor 默认为：RuntimeException OR Error
	public User update(User user) throws DatabaseException {
		User result = userDao.save(user);
		result.setAttributes(otherBusiness());
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class) //rollbackFor 默认为：RuntimeException OR Error
	public void delete(Long id) throws DatabaseException {
		User user = userDao.get(id);
		if(user != null) {
			userDao.delete(id);
		}
	}
	
	private List<Attribute> otherBusiness() {
		List<Attribute> myBusinessAttributes = new ArrayList<>();
		myBusinessAttributes.add(new Attribute("1", "a"));
		myBusinessAttributes.add(new Attribute("2", "b"));
		
		return myBusinessAttributes;
	}

}
