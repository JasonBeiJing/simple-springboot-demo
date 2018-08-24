package com.springboot.demo.controller.advice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springboot.demo.exception.EntityNotFoundException;
import com.springboot.demo.exception.IllegalVariableException;
import com.springboot.demo.exception.MyException;

@ControllerAdvice
public class ExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public List<SerializableException> handleEntityNotFoundException(EntityNotFoundException exception) {
		return Collections.singletonList(generateSerializableException(exception));
	}
	
	@ExceptionHandler(IllegalVariableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<SerializableException> handleIllegalVariableException(IllegalVariableException exception) {
		return Collections.singletonList(generateSerializableException(exception));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<SerializableException> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<ObjectError> errors = exception.getBindingResult().getAllErrors();
		List<SerializableException> out = new ArrayList<>(errors.size());
		for (ObjectError objectError : errors) {
			SerializableException ei = new SerializableException(objectError.getCode(), objectError.getDefaultMessage());
//			ei.setObjectName(objectError.getObjectName());
//			if (objectError instanceof FieldError) {
//				FieldError fieldError = (FieldError) objectError;
//				ei.setField(fieldError.getField());
//				ei.setRejectedValue(fieldError.getRejectedValue() + "");
//			}
			out.add(ei);
		}
		return out;
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public List<SerializableException> handleThrowable(Throwable exception) {
		return Collections.singletonList(generateSerializableException(exception));
	}
	
	
	private SerializableException generateSerializableException(Throwable e){
		if(e instanceof MyException) {
			MyException me = (MyException)e;
			return new SerializableException(me.getErrorCode(), messageSource.getMessage(me.getErrorCode(), me.getArgs(), me.getDefaultErrorMessage(), LocaleContextHolder.getLocale()));
		}else {
			if(logger.isWarnEnabled()) {
				logger.error("UNKNOWN_ERROR", e);
			}
			return new SerializableException("UNKNOWN_ERROR", "未知错误");
		}
		
	}

	static class SerializableException implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -5486003551346365419L;
		private String errorCode;
		private String errorMessage;
		
		public SerializableException() {
			super();
		}


		public SerializableException(String errorCode, String errorMessage) {
			super();
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
		}


		public String getErrorCode() {
			return errorCode;
		}


		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}


		public String getErrorMessage() {
			return errorMessage;
		}


		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

	}
}