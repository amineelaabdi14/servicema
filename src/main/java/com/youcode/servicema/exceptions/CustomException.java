package com.youcode.servicema.exceptions;

public class CustomException extends  RuntimeException{
    CustomException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
