package com.ibm.activity.ordersservice.exception;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = -8504793288852238602L;

	public ResourceNotFoundException() {
		super();

	}

	public ResourceNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

	public ResourceNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public ResourceNotFoundException(String arg0) {
		super(arg0);

	}

	public ResourceNotFoundException(Throwable arg0) {
		super(arg0);

	}

}
