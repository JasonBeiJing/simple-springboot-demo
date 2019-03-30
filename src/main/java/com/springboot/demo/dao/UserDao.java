package com.springboot.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;
	
	public User getById(Long id) throws DatabaseException {
		String sql = "SELECT id, name, phone FROM user WHERE id = :id";
		Map<String, Long> paramMap = new HashMap<>();
		paramMap.put("id", id);
		
		try {
			return jdbcTemplate.queryForObject(sql, paramMap, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new User(rs.getLong(1), rs.getString(2), rs.getString(3));
				}
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DatabaseException(DatabaseException.ERROR_CODE.DB_GET_ERROR, "database error : failed to get user entity", e);
		}
	}
	
	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Throwable.class)
	public void deleteById(Long id) {
		logger.warn(" == delete the user with id: {}  ===== ", id);
	}
	

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Throwable.class)
	public User save(User user) throws DatabaseException {
//		MapSqlParameterSource paramSource = new MapSqlParameterSource();
//		paramSource.addValue("id", user.getId());
//		paramSource.addValue("name", user.getName());
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);

		if(user.getId()==null) {
			//insert
			KeyHolder generatedKeyHolder = new GeneratedKeyHolder(); 
			jdbcTemplate.update("INSERT INTO user (name, phone) VALUES (:name, :phone)", paramSource, generatedKeyHolder);
			user.setId(generatedKeyHolder.getKey().longValue());
		}else {
			//update
			jdbcTemplate.update("UPDATE user SET name= :name, phone= :phone WHERE id= :id", paramSource);
		}
		return getById(user.getId());
	}

	public List<User> list(String name, String phone, String orderBy, boolean asc, int offset, int limit){
		StringBuilder sql = new StringBuilder("SELECT id, name, phone FROM user WHERE 1=1");
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		if(StringUtils.isNotBlank(name)) {
			paramSource.addValue("name", "%" + name + "%");
			sql.append(" AND name =:name");
		}
		
		if(StringUtils.isNotBlank(phone)) {
			paramSource.addValue("phone", phone);
			sql.append(" AND phone =:phone");
		}
		
		return jdbcTemplate.query(sql.toString(), paramSource, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setName(rs.getString("name"));
				u.setPhone(rs.getString("phone"));
				return u;
			}
		});
	}
}
