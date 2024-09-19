package com.example.ramesh.demo.ramesh.Exceptions;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
	public BlogApiException(HttpStatus status, String message) {
		super(String.format("%s and its status is %s.",message,status));
		this.setStatus(status);
		this.setMessage(message);
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
