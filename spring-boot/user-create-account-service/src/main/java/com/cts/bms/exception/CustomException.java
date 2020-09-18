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
		

		@ExceptionHandler(InvalidPanContact.class)
		public final ResponseEntity<Object> userNotFoundException
									(Exception ex, WebRequest request) throws Exception {
			
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
			return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
		
		}
		
		@ExceptionHandler(AccountAlreadyExist.class)
		public final ResponseEntity<Object> accountAlreadyExist
									(Exception ex, WebRequest request) throws Exception {
			
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
			return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
		
		}
		
		@ExceptionHandler(MaximumAccount.class)
		public final ResponseEntity<Object> maximumAccount
									(Exception ex, WebRequest request) throws Exception {
			
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
			return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
		
		}
		
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
			return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
		
		}
		


}
