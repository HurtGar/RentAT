package com.springboot.AT.exception;

public class IllegalArgumentException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalArgumentException() {
		super();
	}
	
	public IllegalArgumentException(String message) {
		super(message);
	}
}
