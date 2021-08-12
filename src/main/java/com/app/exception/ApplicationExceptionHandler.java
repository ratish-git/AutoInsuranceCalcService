package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.dto.ErrorClass;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApplicationException.class)
	protected ResponseEntity<Object> handleException(ApplicationException ex) throws ApplicationException {

		ErrorClass errorClass = new ErrorClass(ex.getErrorCode(), ex.getErrorMessage());
		return buildResponseEntity(ex.getHttpStatus(), errorClass);

	}

	private ResponseEntity<Object> buildResponseEntity(HttpStatus status, ErrorClass error) {
		return new ResponseEntity<>(error, status);
	}

}
