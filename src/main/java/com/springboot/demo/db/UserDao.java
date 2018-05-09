package com.springboot.demo.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
