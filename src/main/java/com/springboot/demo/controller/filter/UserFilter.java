package com.springboot.demo.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;

@Order(0)
@WebFilter(filterName = "UserFilter", urlPatterns = "/*")
public class UserFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.err.println(" ---------- UserFilter init(...) ----------");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.err.println(" ---------- UserFilter doFilter(...) ----------");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.err.println(" ---------- UserFilter destroy(...) ----------");
	}

}
