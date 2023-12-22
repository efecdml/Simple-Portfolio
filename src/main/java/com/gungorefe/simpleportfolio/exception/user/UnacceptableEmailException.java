package com.gungorefe.simpleportfolio.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UnacceptableEmailException extends RuntimeException {
    public UnacceptableEmailException(String message) {
        super(message);
    }
}
