package com.youcode.servicema.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.rmi.UnexpectedException;
import java.util.Date;
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
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity handleIllegalArgumentException(UnexpectedTypeException ex) {
        return new ResponseEntity<>(new HashMap<>(){
            {
                put("message", ex.getMessage());
            }
        }, HttpStatus.BAD_REQUEST);
    }

}
