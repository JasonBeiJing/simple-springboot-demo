package com.springboot.demo.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.demo.entity.User;

@Repository
public class UserDao {

	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;
	
	public User get(Long id) {
		String sql = "SELECT id, name FROM user WHERE id = :id";
		Map<String, Long> paramMap = new HashMap<>();
		paramMap.put("id", id);
		
		return jdbcTemplate.queryForObject(sql, paramMap, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(rs.getLong(1), rs.getString(2));
			}
		});
	}

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	public User save(User user) {
		if(user.getId()==null) {
			//insert
		}else {
			//update
		}
		return null;
	}
}
