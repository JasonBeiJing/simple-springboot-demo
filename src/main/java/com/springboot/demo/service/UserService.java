package com.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.springboot.demo.dao.UserDao;
import com.springboot.demo.entity.Attribute;
import com.springboot.demo.entity.User;
import com.springboot.demo.exception.DatabaseException;
import com.springboot.demo.exception.EntityNotFoundException;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;
	
	public User get(Long id) throws DatabaseException, EntityNotFoundException {
		System.err.println(TransactionSynchronizationManager.isActualTransactionActive() + " ========= " + TransactionSynchronizationManager.isCurrentTransactionReadOnly() + " ===== " + TransactionSynchronizationManager.getCurrentTransactionName());
		
		User user = userDao.get(id);
		if(user == null) {
			logger.warn("user not found with id: {}", id);
			throw new EntityNotFoundException(EntityNotFoundException.ERROR_CODE.USER_NOT_FOUND, "user not found : " + id);
		}
		user.setAttributes(otherBusiness());
		return user;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class) //rollbackFor 默认为：RuntimeException OR Error
	public User create(User user) throws DatabaseException {
		System.err.println(TransactionSynchronizationManager.isActualTransactionActive() + " ========= " + TransactionSynchronizationManager.isCurrentTransactionReadOnly() + " ===== " + TransactionSynchronizationManager.getCurrentTransactionName());
		
		if(user.getId()!=null) {
			user.setId(null);
		}
		User result = userDao.save(user);
		result.setAttributes(otherBusiness());
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class) //rollbackFor 默认为：RuntimeException OR Error
	public User update(User user) throws DatabaseException {
		System.err.println(TransactionSynchronizationManager.isActualTransactionActive() + " ========= " + TransactionSynchronizationManager.isCurrentTransactionReadOnly() + " ===== " + TransactionSynchronizationManager.getCurrentTransactionName());
		
		if(user.getId()==null) {
			throw new IllegalArgumentException("ID_REQUIRED");
		}
		User result = userDao.save(user);
		result.setAttributes(otherBusiness());
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class) //rollbackFor 默认为：RuntimeException OR Error
	public void delete(Long id) throws DatabaseException {
		System.err.println(TransactionSynchronizationManager.isActualTransactionActive() + " ========= " + TransactionSynchronizationManager.isCurrentTransactionReadOnly() + " ===== " + TransactionSynchronizationManager.getCurrentTransactionName());
		
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
