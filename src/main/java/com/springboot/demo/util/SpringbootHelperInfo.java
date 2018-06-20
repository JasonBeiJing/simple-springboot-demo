package com.springboot.demo.util;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.CollectionUtils;

@Component
public class SpringbootHelperInfo {

private static final Logger logger = LoggerFactory.getLogger(SpringbootHelperInfo.class);

	@Autowired(required = false)
	private List<JdbcOperations> jdbcOperations;
	@Autowired(required = false)
	private List<NamedParameterJdbcOperations> namedParameterJdbcOperations;
	@Autowired(required = false)
	private List<PlatformTransactionManager> transactionManagers;
	@Autowired(required = false)
	private List<DataSource> datasources;
	
	@PostConstruct
	public void init() {
		if(logger.isWarnEnabled()) {
			logger.warn("========================");
			logger.warn("=============");
			mysqlConfig();
			logger.warn("=============");
			logger.warn("========================");
		}
	}
	
	private void mysqlConfig() {
		if(!CollectionUtils.isEmpty(datasources)) {
			for(DataSource ds:datasources) {				
				logger.warn("##### MYSQL DATASOURCE TYPE : {}", ds.getClass().getCanonicalName());
			}
		}else {
			logger.warn("##### MYSQL DATASOURCE TYPE --> NOT FOUND");
		}
		if(!CollectionUtils.isEmpty(jdbcOperations)) {			
			for(JdbcOperations jo:jdbcOperations) {
				logger.warn("##### MYSQL JdbcOperations : {}", jo.getClass().getCanonicalName());
			}
		}else {
			logger.warn("##### MYSQL JdbcOperations --> NOT FOUND");
		}
		if(!CollectionUtils.isEmpty(namedParameterJdbcOperations)) {			
			for(NamedParameterJdbcOperations npjo:namedParameterJdbcOperations) {
				logger.warn("##### MYSQL NamedParameterJdbcOperations : {}", npjo.getClass().getCanonicalName());
			}
		}else {
			logger.warn("##### MYSQL NamedParameterJdbcOperations --> NOT FOUND");
		}
		if(!CollectionUtils.isEmpty(transactionManagers)) {
			for(PlatformTransactionManager transactionManager:transactionManagers) {
				logger.warn("##### MYSQL TRANSACTION MANAGER : {} ",  transactionManager.getClass().getCanonicalName());
			}
		}else {
			logger.warn("##### MYSQL TRANSACTION MANAGER --> NOT FOUND ");
		}
	}
	
}
