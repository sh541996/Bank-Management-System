package com.cts.bms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class PanOrFundIdInvalid extends Exception {

	public PanOrFundIdInvalid(String message) {
		super(message);
	}	
}
