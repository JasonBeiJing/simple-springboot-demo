package com.springboot.demo.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.springboot.demo.domain.entity.User;

@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory connectionFactory){
		RedisTemplate<String, User> userRedisTemplate = new RedisTemplate<>();
		userRedisTemplate.setKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
		userRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
		userRedisTemplate.setConnectionFactory(connectionFactory);
		return userRedisTemplate;
	}
}
