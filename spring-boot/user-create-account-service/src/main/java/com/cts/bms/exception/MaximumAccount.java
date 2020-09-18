package com.cts.bms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaximumAccount extends Exception {

	public MaximumAccount(String message) {
		super(message);
	}

}
