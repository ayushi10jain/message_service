package com.plivo.message.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
	private Date date;
	private String message;
	private String description;
	private HttpStatus methodNotAllowed;
	private int value;

	

	public ErrorDetails(Date date, String message, String description, HttpStatus methodNotAllowed, int value) {
		this.date = date;
		this.message = message;
		this.description = description;
		this.methodNotAllowed = methodNotAllowed;
		this.value = value;
	}

	

	public ErrorDetails(Date date, String message, String description) {
		
		this.date = date;
		this.message = message;
		this.description = description;
	}



	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HttpStatus getMethodNotAllowed() {
		return methodNotAllowed;
	}

	public void setMethodNotAllowed(HttpStatus methodNotAllowed) {
		this.methodNotAllowed = methodNotAllowed;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	

}
