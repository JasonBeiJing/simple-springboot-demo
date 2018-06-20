package com.springboot.demo.controller.advice;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public SerializableException handleEntityNotFoundException(EntityNotFoundException exception) {
		return generateSerializableException(exception);
	}
	
	@ExceptionHandler(IllegalVariableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public SerializableException handleIllegalVariableException(IllegalVariableException exception) {
		return generateSerializableException(exception);
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public SerializableException handleThrowable(Throwable exception) {
		return generateSerializableException(exception);
	}
	
	
	private static SerializableException generateSerializableException(Throwable e){
		if(e instanceof MyException) {
			MyException me = (MyException)e;
			return new SerializableException(me.getErrorCode(), me.getErrorMessage());
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