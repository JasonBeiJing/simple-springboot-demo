package com.springboot.demo.web.advice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class DataBinderAdvice{
	private static final Logger logger = LoggerFactory.getLogger(DataBinderAdvice.class);
	
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
		//binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true));
	}
}
