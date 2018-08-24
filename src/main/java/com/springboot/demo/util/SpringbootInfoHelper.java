package com.springboot.demo.util;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
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
	@Autowired(required = false)
	private List<RedisConnectionFactory> redisConnectionFactories;
	@SuppressWarnings("rawtypes")
	@Autowired(required = false)
	private List<RedisOperations> ros;
	@Autowired(required = false)
	private List<MessageSource> mss;
	
	
	@PostConstruct
	public void init() {
		if(logger.isInfoEnabled()) {
			logger.info("========================");
			logger.info("=============");
			mysqlConfig();
			mvc();
			redis();
			i18n();
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
		if(!CollectionUtils.isEmpty(wmcs)) {			
			for(WebMvcConfigurer wmc:wmcs) {
				logger.info("WebMvcConfigurer ====> " + wmc.getClass().getCanonicalName());
			}
		}else {
			logger.info(" ======= NO WebMvcConfigurer found ===========");
		}
	}
	
	public void redis() {
		if(!CollectionUtils.isEmpty(redisConnectionFactories)) {
			for(RedisConnectionFactory rcf:redisConnectionFactories) {
				logger.info("RedisConnectionFactory ===> " + rcf.getClass().getCanonicalName());
				RedisConnection rc = rcf.getConnection();
				if(rc !=null ) {
					logger.info("RedisConnection ===> " + rc.getClass().getCanonicalName());
				}
			}
		}else{
			logger.info(" ======= NO RedisConnectionFactory found ===========");
		}
		if(!CollectionUtils.isEmpty(ros)) {
			for(@SuppressWarnings("rawtypes") RedisOperations ro:ros) {
				logger.info("RedisOperations ===> " + ro.getClass().getCanonicalName());
			}
		}else{
			logger.info(" ======= NO RedisOperations found ===========");
		}
	}
	
	private void i18n() {
		if(mss!=null) {
			for(MessageSource ms:mss) {
				logger.info("MessageSource -------- >" + ms.getClass().getCanonicalName());
			}			
		}else {
			logger.info(" ======= NO MessageSource found ===========");
		}
	}
	
}
