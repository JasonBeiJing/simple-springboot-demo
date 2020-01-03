package com.springboot.demo.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springboot.demo.domain.entity.User;

@Component
public class UserValidator implements Validator{

	private static enum ERROR_CODE {
		PHONE_MISSING
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		if(User.class.isAssignableFrom(clazz)) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", ERROR_CODE.PHONE_MISSING.name(), new Object[] {}, "phone number is required");
	}

}
