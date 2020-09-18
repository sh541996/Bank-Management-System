package com.cts.bms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PanOrAccountInvalid extends Exception {

	public PanOrAccountInvalid(String message) {
		super(message);

	}	

}
