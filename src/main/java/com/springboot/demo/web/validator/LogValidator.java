package com.springboot.demo.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springboot.demo.domain.entity.Log;

@Component
public class LogValidator implements Validator {

	private static enum ERROR_CODE {
		KEY_REQUIRED
	}

	private static final String FIELD_KEY = "key";

	@Override
	public boolean supports(Class<?> clazz) {
		if (Log.class.isAssignableFrom(clazz)) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_KEY, ERROR_CODE.KEY_REQUIRED.name(), new Object[] {},
				"key is required");
	}

}
