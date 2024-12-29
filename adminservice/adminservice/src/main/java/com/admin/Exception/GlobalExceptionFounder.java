package com.admin.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionFounder {



		@ExceptionHandler(AdminNotFoundException.class)
		public ResponseEntity<String> handleUserNotFoundException(AdminNotFoundException ex) {
	       
		        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleGenericException(Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	    }
		
}
