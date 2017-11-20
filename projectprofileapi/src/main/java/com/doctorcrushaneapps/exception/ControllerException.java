package com.doctorcrushaneapps.exception;

public class ControllerException extends Exception {

	private static final long serialVersionUID = -5002185607789061147L;

	private String errorCode = "Controller Exception";
	
	public ControllerException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
}
