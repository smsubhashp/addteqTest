package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionClass {
	@ExceptionHandler(value = FileNotFoundException.class)
	public ResponseEntity<Object> exception(FileNotFoundException exception) {
		return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = IOError.class)
	public ResponseEntity<Object> exception(IOError exception) {
		return new ResponseEntity<>("Product not found", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
