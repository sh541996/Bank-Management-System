package com.cts.bms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FundNameInvalid extends Exception {

	public FundNameInvalid(String message) {
		super(message);
	}

}
