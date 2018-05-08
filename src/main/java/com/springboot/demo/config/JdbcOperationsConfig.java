package com.springboot.demo.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.CollectionUtils;

@Configuration
public class JdbcOperationsConfig {

	private static final Logger logger = LoggerFactory.getLogger(JdbcOperationsConfig.class);
	
	/**
	 * springboot helps us to generate the DataSource + JdbcTemplate + PlatformTransactionManager
	 * so, by default JdbcTemplate and transaction are supported automatically
	 * @return
	 */
	
	@Bean
	public NamedParameterJdbcOperations namedParameterJdbcOperations(JdbcTemplate jdbcTemplate, List<PlatformTransactionManager> transactionManagers) {
		if(logger.isInfoEnabled()) {
			logMysqlConfig(jdbcTemplate, transactionManagers);
		}
		return new NamedParameterJdbcTemplate(jdbcTemplate);
	}
	
	private void logMysqlConfig(JdbcTemplate jdbcTemplate, List<PlatformTransactionManager> transactionManagers) {
		if(jdbcTemplate!=null) {			
			logger.info("========================");
			logger.info("============");
			logger.info("MYSQL DATASOURCE TYPE : " + jdbcTemplate.getDataSource().getClass().getCanonicalName());
			logger.info("============");
			logger.info("========================");
		}
		if(!CollectionUtils.isEmpty(transactionManagers)) {
			logger.info("########################");
			logger.info("############");
			for(PlatformTransactionManager transactionManager:transactionManagers) {
				logger.info("MYSQL TRANSACTION MANAGER : " + transactionManager.getClass().getCanonicalName());
			}
			logger.info("############");
			logger.info("########################");
		}
	}
}
