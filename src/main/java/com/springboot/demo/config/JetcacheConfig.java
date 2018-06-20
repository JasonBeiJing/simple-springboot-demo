package com.springboot.demo.config;

import org.springframework.context.annotation.Configuration;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;

@EnableMethodCache(basePackages = "com.springboot.demo")
@EnableCreateCacheAnnotation
@Configuration
public class JetcacheConfig {

}
