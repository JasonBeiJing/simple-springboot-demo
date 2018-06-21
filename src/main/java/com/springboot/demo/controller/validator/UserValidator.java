package com.springboot.demo.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springboot.demo.entity.User;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		if(User.class.isAssignableFrom(clazz)) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "a", "a_missing", null, "a is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "b", "b_missing", null, "b is required");
	}

}
