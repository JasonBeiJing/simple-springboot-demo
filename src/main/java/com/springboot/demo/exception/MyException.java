package com.springboot.demo.exception;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6810553140147813877L;
	private String errorCode;
	private String errorMessage;
	private Throwable exception; 
	
	
	public MyException(String errorCode, String errorMessage, Throwable exception) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.setException(exception);
	}
	public MyException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Throwable getException() {
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}
}
