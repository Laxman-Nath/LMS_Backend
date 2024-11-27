package com.lms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter

public class CustomException extends RuntimeException {
    private String code;

    public CustomException(String code, String message) {
        super(message);  
        this.code = code;
    }

   
}

