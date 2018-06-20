package com.springboot.demo.exception;

public class IllegalVariableException extends MyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8624601504786189017L;

	public IllegalVariableException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
