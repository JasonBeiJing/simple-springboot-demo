package com.springboot.demo.config.configuration_properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.datasource.hikari")
@Configuration
public class TestConfigurationPropertiesConfig {

	private String poolName;
	private long connectionTimeout;
	
	
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	@Bean
	public TestConfigurationProperties testConfigurationProperties() {
		return new TestConfigurationProperties();
	}
	
	public String getPoolName() {
		return poolName;
	}
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
	public long getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(long connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
}
