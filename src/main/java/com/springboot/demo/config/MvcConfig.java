package com.springboot.demo.config;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboot.demo.controller.interceptor.UserInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Autowired
	private UserInterceptor userInterceptor;
	
	@Bean
    public LocaleResolver localeResolver(){
        return new NativeLocaleResolver();
    }

    protected static class NativeLocaleResolver implements LocaleResolver{
        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String lang = request.getParameter("lang");
            String localeString = request.getParameter("locale");;
            Locale locale = Locale.getDefault();
            if(!StringUtils.isEmpty(lang)){
                String[] split = lang.split("_");
                locale = new Locale(split[0],split[1]);
            }else if(!StringUtils.isEmpty(localeString)) {
            	 String[] split = localeString.split("_");
                 locale = new Locale(split[0],split[1]);
            }else if((locale = request.getLocale())!=null) {
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        }
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userInterceptor);
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addCorsMappings(registry);
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.extendMessageConverters(converters);
	}

	
}
