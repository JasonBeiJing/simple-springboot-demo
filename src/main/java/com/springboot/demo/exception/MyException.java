package com.springboot.demo.exception;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6810553140147813877L;
	private String errorCode;
	private String defaultErrorMessage;
	private Object[] args;
	
	
	public MyException(String errorCode, String defaultErrorMessage, Throwable exception, Object... args) {
		super(exception);
		this.errorCode = errorCode;
		this.defaultErrorMessage = defaultErrorMessage;
		this.args = args;
	}
	
	public MyException(String errorCode, Object[] args) {
		this(errorCode, null, null, args);
	}
	
	public MyException(String errorCode) {
		this(errorCode, null, null);
	}
	
	
	public MyException() {
		super();
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getDefaultErrorMessage() {
		return defaultErrorMessage;
	}
	public void setDefaultErrorMessage(String defaultErrorMessage) {
		this.defaultErrorMessage = defaultErrorMessage;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}
