package com.springboot.demo.exception;

public class DatabaseException extends MyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5640775043255125628L;
	
	public static enum ERROR_CODE{
		DB_GET_ERROR, DB_CREATE_ERROR, DB_DELETE_ERROR, DB_UPDATE_ERROR
	}
	
	public DatabaseException(ERROR_CODE errorCode, String errorMessage, Throwable exception) {
		super(errorCode.name(), errorMessage, exception);
	}
}
