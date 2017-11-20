package com.doctorcrushaneapps.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 444843022501249200L;
	
	private String errorCode = "Service Exception";
	
	public DaoException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
}
