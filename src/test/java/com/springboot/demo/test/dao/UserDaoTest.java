package com.springboot.demo.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.demo.dao.UserDao;
import com.springboot.demo.entity.User;
import com.springboot.demo.exception.DatabaseException;

public class UserDaoTest extends AbstractBaseDaoTest{

	@Autowired
	private UserDao userDao;
	
	@Sql(statements = {"create table aaa", "add index xxx", "insert test data"})//准备schema, index, data ...
	@Transactional
	@Rollback //测试完回滚到初始状态
	@Test
	public void testSave() throws DatabaseException {
		User user = new User();
		userDao.save(user);
	}
}
