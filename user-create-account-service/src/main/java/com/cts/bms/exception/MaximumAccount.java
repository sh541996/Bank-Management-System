package com.cts.bms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MaximumAccount extends Exception {

	public MaximumAccount(String message) {
		super(message);
	}

}
