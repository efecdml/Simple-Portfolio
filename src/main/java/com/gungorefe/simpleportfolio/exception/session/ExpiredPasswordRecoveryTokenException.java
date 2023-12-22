package com.gungorefe.simpleportfolio.exception.session;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ExpiredPasswordRecoveryTokenException extends RuntimeException {
    public ExpiredPasswordRecoveryTokenException(String message) {
        super(message);
    }
}
