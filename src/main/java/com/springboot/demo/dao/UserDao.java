package com.springboot.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.demo.entity.User;
import com.springboot.demo.exception.DatabaseException;

@Repository
public class UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	private static final String CACHE_NAME = "USER_DAO_CACHE_";
	
	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate<String, User> userRedisTemplate;
	
	
	public User get(Long id) throws DatabaseException {
		User user = userRedisTemplate.opsForValue().get(CACHE_NAME + id);
		if(logger.isDebugEnabled()) {			
			logger.debug(" === got user from cache ? {} ==== ", user == null ? "NO" : "YES");
		}
		if(user == null) {
			userRedisTemplate.opsForValue().set(CACHE_NAME + id, user = getEntity(id), 30, TimeUnit.SECONDS);
		}
		return user;
	}
	
	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	public void delete(Long id) {
		logger.warn(" == delete the user with id: {}  ===== ", id);
	}
	
	private User getEntity(Long id) throws DatabaseException {
		String sql = "SELECT id, name FROM user WHERE id = :id";
		Map<String, Long> paramMap = new HashMap<>();
		paramMap.put("id", id);
		
		try {
			return jdbcTemplate.queryForObject(sql, paramMap, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new User(rs.getLong(1), rs.getString(2));
				}
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DatabaseException(DatabaseException.ERROR_CODE.DB_GET_ERROR, "database error : failed to get user entity", e);
		}
	}

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	public User save(User user) throws DatabaseException {
//		MapSqlParameterSource paramSource = new MapSqlParameterSource();
//		paramSource.addValue("id", user.getId());
//		paramSource.addValue("name", user.getName());
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);

		if(user.getId()==null) {
			//insert
			KeyHolder generatedKeyHolder = new GeneratedKeyHolder(); 
			jdbcTemplate.update("INSERT INTO user (name) VALUES (:name)", paramSource, generatedKeyHolder);
			user.setId(generatedKeyHolder.getKey().longValue());
		}else {
			//update
			jdbcTemplate.update("UPDATE user SET name= :name WHERE id= :id", paramSource);
		}
		return get(user.getId());
	}
}
