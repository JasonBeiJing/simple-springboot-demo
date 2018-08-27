package com.springboot.demo.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springboot.demo.entity.User;

@Component
public class UserValidator implements Validator{

	private static enum ERROR_CODE {
		A_MISSING,
		B_MISSING
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "a", ERROR_CODE.A_MISSING.name(), new Object[] {}, "a is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "b", ERROR_CODE.B_MISSING.name(), new Object[] {}, "b is required");
	}

}
