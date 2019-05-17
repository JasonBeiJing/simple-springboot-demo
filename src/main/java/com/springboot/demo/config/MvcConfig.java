package com.huawei.it.acc.sign.template.impl.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Bean
	public LocaleResolver localeResolver() {
		return new NativeLocaleResolver();
	}

	protected static class NativeLocaleResolver implements LocaleResolver {
		@Override
		public Locale resolveLocale(HttpServletRequest request) {
			String lang = request.getParameter("lang");
			String localeString = request.getParameter("locale");
			Locale locale = Locale.getDefault();
			if (StringUtils.isNotBlank(lang)) {
				locale = LocaleUtils.toLocale(lang);
			} else if (StringUtils.isNotBlank(localeString)) {
				locale = LocaleUtils.toLocale(localeString);
			} else if (request.getLocale() != null) {
				locale = request.getLocale();
			}
			return locale;
		}

		@Override
		public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		}
	}
}
