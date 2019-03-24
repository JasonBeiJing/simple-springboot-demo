package com.springboot.demo.controller.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;

@Order(0)
@WebFilter(filterName = "LocaleFilter", urlPatterns = "/*")
public class LocaleFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(LocaleFilter.class);
	
	private static final String LANGUAGE_CODE = "language";
	private static final String COUNTRY_CODE = "country";
	private static final String LANG = "lang";
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info(" ---------- LocaleFilter init(...) ----------");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Locale locale = request.getLocale(); //Accept-Language: en-US
		
		if(locale == null) {
			String language = null;
			String country = null;
			String lang = null;
			if(StringUtils.isNotBlank(language = request.getParameter(LANGUAGE_CODE)) && StringUtils.isNotBlank(country = request.getParameter(COUNTRY_CODE))) { //?language=en&country=US
				locale = new Locale(language, country);
			}else if(StringUtils.isNotBlank(lang = request.getParameter(LANG))) { //?lang=en_US
				String[] s = StringUtils.split(lang, "_");
				locale = new Locale(s[0], s[1]);
			}else {
				locale = Locale.US;
			}
		}
		
		LocaleContextHolder.setLocale(locale);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.info(" ---------- LocaleFilter destroy(...) ----------");
	}
}
