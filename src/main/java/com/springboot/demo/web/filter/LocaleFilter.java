package com.springboot.demo.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Order(0)
@WebFilter(filterName = "LocaleFilter", urlPatterns = "/*")
public class LocaleFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(LocaleFilter.class);
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info(" ---------- LocaleFilter init(...) ----------");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.info(" ---------- LocaleFilter destroy(...) ----------");
	}
}
