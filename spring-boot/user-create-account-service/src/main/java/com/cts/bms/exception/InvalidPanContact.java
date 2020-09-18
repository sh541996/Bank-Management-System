package com.cts.bms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidPanContact extends Exception {

	public InvalidPanContact(String message) {
		super(message);
	}
}
