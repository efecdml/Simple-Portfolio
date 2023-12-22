package com.gungorefe.simpleportfolio.exception.session;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPasswordRecoveryTokenException extends RuntimeException {
    public InvalidPasswordRecoveryTokenException(String message) {
        super(message);
    }
}
