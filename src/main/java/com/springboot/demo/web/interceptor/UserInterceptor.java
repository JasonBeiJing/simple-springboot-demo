package com.springboot.demo.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springboot.demo.web.namespace.ApiNamespace;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		if(request.getRequestURI().startsWith(ApiNamespace.URI_USERS)) {			
			System.err.println(" ------ UserInterceptor --- preHandle(...) --------");
			//....
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
		if(request.getRequestURI().startsWith(ApiNamespace.URI_USERS)) {			
			System.err.println(" ------ UserInterceptor --- afterCompletion(...) --------");
			//....
		}
		super.afterCompletion(request, response, handler, ex);
	}
}
