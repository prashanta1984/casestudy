package com.ibm.activity.convertcurrency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ConCurrencyExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<CoreError> handleException(ResourceNotFoundException reException) {
		CoreError coreError= new CoreError();
		coreError.setAppliedValue(reException.getMessage());
		coreError.setRejectedValue(reException.getMessage());
		coreError.setErrorMessage("Requested resource not available");
		coreError.setErrorCode(HttpStatus.BAD_REQUEST.value()+"");
		return new ResponseEntity<CoreError>(coreError, HttpStatus.BAD_REQUEST);
	}

}
