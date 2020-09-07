package com.ibm.activity.ordersservice.exception;

public class ResourceCreationException extends Exception {

	private static final long serialVersionUID = 6037635606160814032L;

	public ResourceCreationException() {
		super();

	}

	public ResourceCreationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public ResourceCreationException(String message, Throwable cause) {
		super(message, cause);

	}

	public ResourceCreationException(String message) {
		super(message);

	}

	public ResourceCreationException(Throwable cause) {
		super(cause);

	}

}
