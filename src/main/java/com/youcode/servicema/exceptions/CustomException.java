package com.youcode.servicema.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import org.springframework.http.HttpStatus;



public class CustomException extends RuntimeException {


    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
