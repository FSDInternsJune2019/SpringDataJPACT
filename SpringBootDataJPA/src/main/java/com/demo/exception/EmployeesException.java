package com.demo.exception;

public class EmployeesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public EmployeesException(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
}
