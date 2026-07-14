package com.example.demo.exception;

public class ConnectionException extends SystemException{
	
	public ConnectionException(String message) {
		super(message);
	}
	
	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
