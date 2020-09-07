package com.ibm.activity.ordersservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrdersExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<CoreError> handleException(ResourceNotFoundException reException) {
		CoreError coreError = new CoreError();
		coreError.setAppliedValue(reException.getMessage());
		coreError.setRejectedValue(reException.getMessage());
		coreError.setErrorMessage("Requested resource not available");
		coreError.setErrorCode(HttpStatus.BAD_REQUEST.value() + "");
		return new ResponseEntity<CoreError>(coreError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceCreationException.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<CoreError> handleException(ResourceCreationException creationException) {
		CoreError coreError = new CoreError();
		coreError.setAppliedValue(creationException.getMessage());
		coreError.setRejectedValue(creationException.getMessage());
		coreError.setErrorCode(HttpStatus.EXPECTATION_FAILED + "");
		coreError.setErrorMessage("Transactional Error");
		return new ResponseEntity<CoreError>(coreError, HttpStatus.EXPECTATION_FAILED);
	}
}
