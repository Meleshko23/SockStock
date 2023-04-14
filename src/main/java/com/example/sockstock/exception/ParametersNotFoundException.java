package com.example.sockstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParametersNotFoundException extends RuntimeException {
    public ParametersNotFoundException(String message) {
        super(message);
    }
}
