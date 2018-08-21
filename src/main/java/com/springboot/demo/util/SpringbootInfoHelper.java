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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SpringbootInfoHelper {

private static final Logger logger = LoggerFactory.getLogger(SpringbootInfoHelper.class);

	@Autowired(required = false)
	private List<JdbcOperations> jdbcOperations;
	@Autowired(required = false)
	private List<NamedParameterJdbcOperations> namedParameterJdbcOperations;
	@Autowired(required = false)
	private List<PlatformTransactionManager> transactionManagers;
	@Autowired(required = false)
	private List<DataSource> datasources;
	@Autowired(required = false)
	private List<WebMvcConfigurer> wmcs;
	
	
	@PostConstruct
	public void init() {
		if(logger.isInfoEnabled()) {
			logger.info("========================");
			logger.info("=============");
			mysqlConfig();
			mvc();
			logger.info("=============");
			logger.info("========================");
		}
	}
	
	private void mysqlConfig() {
		if(!CollectionUtils.isEmpty(datasources)) {
			for(DataSource ds:datasources) {				
				logger.info("##### MYSQL DATASOURCE TYPE : {}", ds.getClass().getCanonicalName());
			}
		}else {
			logger.info("##### MYSQL DATASOURCE TYPE --> NOT FOUND");
		}
		if(!CollectionUtils.isEmpty(jdbcOperations)) {			
			for(JdbcOperations jo:jdbcOperations) {
				logger.info("##### MYSQL JdbcOperations : {}", jo.getClass().getCanonicalName());
			}
		}else {
			logger.info("##### MYSQL JdbcOperations --> NOT FOUND");
		}
		if(!CollectionUtils.isEmpty(namedParameterJdbcOperations)) {			
			for(NamedParameterJdbcOperations npjo:namedParameterJdbcOperations) {
				logger.info("##### MYSQL NamedParameterJdbcOperations : {}", npjo.getClass().getCanonicalName());
			}
		}else {
			logger.info("##### MYSQL NamedParameterJdbcOperations --> NOT FOUND");
		}
		if(!CollectionUtils.isEmpty(transactionManagers)) {
			for(PlatformTransactionManager transactionManager:transactionManagers) {
				logger.info("##### MYSQL TRANSACTION MANAGER : {} ",  transactionManager.getClass().getCanonicalName());
			}
		}else {
			logger.info("##### MYSQL TRANSACTION MANAGER --> NOT FOUND ");
		}
	}
	
	public void mvc() {
		if(wmcs != null) {			
			for(WebMvcConfigurer wmc:wmcs) {
				logger.info("WebMvcConfigurer ====> " + wmc.getClass().getCanonicalName());
			}
		}
	}
	
}
