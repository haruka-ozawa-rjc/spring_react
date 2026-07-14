package com.example.demo.exception;

public class DatabaseException extends SystemException{
	
	public DatabaseException(String message) {
		super(message);
	}
	
	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
}