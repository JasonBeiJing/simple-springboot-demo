package com.springboot.demo.web.advice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.springboot.demo.web.advice.propertyeditors.CustomLocalDateTimeEditor;

@ControllerAdvice
public class DataBinderAdvice{
	private static final Logger logger = LoggerFactory.getLogger(DataBinderAdvice.class);
	
	private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
	
	@Autowired(required=false)
	private List<Validator> validators;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){		
		List<Validator> vas = new ArrayList<Validator>();
		if(!CollectionUtils.isEmpty(validators)){			
			for(Validator validator:validators){
				if(binder.getTarget()!=null && validator.supports(binder.getTarget().getClass())){
					if(logger.isDebugEnabled()) {						
						logger.debug(binder.getTarget().getClass().getCanonicalName() + " GOT VALIDATOR : " + validator.getClass().getCanonicalName());
					}
					vas.add(validator);  
				}
			}
			binder.replaceValidators(vas.toArray(new Validator[vas.size()]));
		}
		binder.setIgnoreUnknownFields(true);
		
		// true-false / on-off / yes-on / 1-0
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true)); 
		//USD / CNY
		binder.registerCustomEditor(Currency.class, new CurrencyEditor());
		// en_US / zh_CN
		binder.registerCustomEditor(Locale.class, new LocaleEditor()); 
		// 2001-07-04T12:08:56.235-07:00
		binder.registerCustomEditor(Date.class, new CustomDateEditor(DATE_FORMAT, true));
		// 2011-12-03T10:15:30+01:00 / 2011-12-03T10:15:30+01:00[Europe/Paris]
		binder.registerCustomEditor(LocalDateTime.class, new CustomLocalDateTimeEditor(DATE_TIME_FORMATTER, true));
	}
	
	
}
