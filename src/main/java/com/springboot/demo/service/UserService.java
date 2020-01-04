package com.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.springboot.demo.dao.UserDao;
import com.springboot.demo.domain.entity.Attribute;
import com.springboot.demo.domain.entity.User;
import com.springboot.demo.domain.exception.DatabaseException;
import com.springboot.demo.domain.exception.EntityNotFoundException;
import com.springboot.demo.domain.exception.IllegalVariableException;
import com.springboot.demo.event.UserMissingEvent;

@Service
public class UserService implements ApplicationEventPublisherAware {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private static final String CACHE_NAME = "USER_DAO_CACHE_";
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate<String, User> userRedisTemplate;
	
	// applicationEventPublisher即ApplicationContext实例，ApplicationContext继承自ApplicationEventPublisher，亦可直接注入
	private ApplicationEventPublisher applicationEventPublisher;
	
	public User getUserById(Long id) throws DatabaseException, EntityNotFoundException {
		User user = userRedisTemplate.opsForValue().get(CACHE_NAME + id);
		logger.debug(" === got user from cache ? {} ==== ", user == null ? "NO" : "YES");
		if(user == null) {
			logger.info(Thread.currentThread().getId() + " === ApplicationEventPublisher ::: " + applicationEventPublisher.getClass().getCanonicalName());
			// 同步找相应的ApplicationListener，然后当前线程立刻执行各Listeners
			applicationEventPublisher.publishEvent(new UserMissingEvent(this, id+""));
			userRedisTemplate.opsForValue().set(CACHE_NAME + id, user = userDao.getById(id), 30, TimeUnit.SECONDS);
		}
		if(user == null) {
			logger.warn("user not found with id: {}", id);
			throw new EntityNotFoundException(EntityNotFoundException.ERROR_CODE.USER_NOT_FOUND, id);
		}
		user.setAttributes(otherBusiness());
		return user;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class) //rollbackFor 默认为：RuntimeException OR Error
	public User createUser(User user) throws DatabaseException, IllegalVariableException {
		if(user.getId()!=null) {
			user.setId(null);
		}
		List<User> users = userDao.list(null, user.getPhone(), null, false, 0, 1);
		if(!CollectionUtils.isEmpty(users)) {
			throw new IllegalVariableException(IllegalVariableException.ERROR_CODE.USER_EXISTED, "user existd with phone number: {0}", user.getPhone());
		}
		User result = userDao.save(user);
		result.setAttributes(otherBusiness());
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class) //rollbackFor 默认为：RuntimeException OR Error
	public User updateUser(User user) throws DatabaseException {
		User result = userDao.save(user);
		result.setAttributes(otherBusiness());
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class) //rollbackFor 默认为：RuntimeException OR Error
	public void deleteUserById(Long id) throws DatabaseException {
		User user = userDao.getById(id);
		if(user != null) {
			userDao.deleteById(id);
		}
	}
	
	public List<Attribute> otherBusiness() {
		List<Attribute> myBusinessAttributes = new ArrayList<>();
		myBusinessAttributes.add(new Attribute("1", "a"));
		myBusinessAttributes.add(new Attribute("2", "b"));
		
		return myBusinessAttributes;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

}
