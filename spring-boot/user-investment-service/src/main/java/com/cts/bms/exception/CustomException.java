package com.cts.bms.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomException extends ResponseEntityExceptionHandler {  
	

	@ExceptionHandler(PanOrAccountInvalid.class)
	public final ResponseEntity<Object> panOrAccountInvalid
								(Exception ex, WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
	
	}
	
	@ExceptionHandler(FundNameInvalid.class)
	public final ResponseEntity<Object> fundNameInvalid
								(Exception ex, WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
	
	}
	
	@ExceptionHandler(NoInvestment.class)
	public final ResponseEntity<Object> noInvestment
								(Exception ex, WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity(exceptionResponse, HttpStatus.NO_CONTENT);
	
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
				MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
				ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
				return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(PanOrFundIdInvalid.class)
	public final ResponseEntity<Object> panOrFundIdInvalid
								(Exception ex, WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity(exceptionResponse, HttpStatus.NO_CONTENT);
	
	}



}
