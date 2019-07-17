package com.company.sys.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 8104987540307496505L;
	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	} 


}
