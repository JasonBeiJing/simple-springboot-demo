package com.springboot.demo.domain.exception;

public class IllegalVariableException extends MyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8624601504786189017L;
	
	public static enum ERROR_CODE{
		ID_MISMATCHED,
		USER_EXISTED,
		INVALID_INPUT
	}

	public IllegalVariableException(ERROR_CODE errorCode, String defaultErrorMessage, Object... args) {
		super(errorCode.name(), defaultErrorMessage, null, args);
	}

}
