package com.springboot.demo.exception;

public class IllegalVariableException extends MyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8624601504786189017L;
	
	public static enum ERROR_CODE{
		ID_MISMATCHED
	}

	public IllegalVariableException(ERROR_CODE errorCode, String defaultErrorMessage) {
		super(errorCode.name(), defaultErrorMessage, null);
	}

}
