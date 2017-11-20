package com.doctorcrushaneapps.exception;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = -5639212221474398823L;
	
	private String errorCode = "Service Exception";
	
	public ServiceException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
}
