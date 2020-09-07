package com.ibm.activity.convertcurrency.exception;

public class CoreError {
	private String errorCode;
	private String errorMessage;
	private String rejectedValue;
	private String appliedValue;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	} 

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(String rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getAppliedValue() {
		return appliedValue;
	}

	public void setAppliedValue(String appliedValue) {
		this.appliedValue = appliedValue;
	}
}
