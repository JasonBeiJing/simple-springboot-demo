package com.springboot.demo.exception;

public class EntityNotFoundException extends MyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5197096112432973449L;
	
	public static enum ERROR_CODE{
		USER_NOT_FOUND,
		ORDER_NOT_FOUND
	}

	
	public EntityNotFoundException(ERROR_CODE errorCode, Object id) {
		super(errorCode.name(), errorCode.name(), null, id);
	}
}
