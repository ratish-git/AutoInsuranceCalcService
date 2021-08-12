package com.app.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;
	private final String errorCode;
	private final String errorMessage;

	public ApplicationException(HttpStatus httpStatus, String errorCode, String errorMessage, String exMessage,
			Throwable e) {
		super(exMessage, e);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
