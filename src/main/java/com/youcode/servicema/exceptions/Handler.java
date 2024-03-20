package com.youcode.servicema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleCustomException(CustomException ex) {
        return new ResponseEntity<>(new HashMap<>(){
            {
                put("message", ex.getMessage());
            }
        }, ex.getHttpStatus());
    }
}
