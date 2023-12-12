package com.gungorefe.simpleportfolio.exception;

import com.gungorefe.simpleportfolio.exception.image.MalformedImageMimeTypeException;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageMimeTypeException;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(MalformedImageMimeTypeException.class)
    public ResponseEntity<?> malformedImageMimeTypeException(MalformedImageMimeTypeException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(UnacceptableImageMimeTypeException.class)
    public ResponseEntity<?> unacceptableImageMimeTypeException(UnacceptableImageMimeTypeException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler(UnacceptableImageNameException.class)
    public ResponseEntity<?> unacceptableImageNameException(UnacceptableImageNameException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_ACCEPTABLE
        );
    }
}
