package com.pair.programming.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = ObjectException.class)
    public ResponseEntity<Object> handleGlobalException(ObjectException authorNotFoundException){
        ErrorResponse errorResponse = new ErrorResponse(authorNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
