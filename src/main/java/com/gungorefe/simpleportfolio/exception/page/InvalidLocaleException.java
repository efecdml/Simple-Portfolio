package com.gungorefe.simpleportfolio.exception.page;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidLocaleException extends RuntimeException {
    public InvalidLocaleException(String message) {
        super(message);
    }
}
