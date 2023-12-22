package com.gungorefe.simpleportfolio.exception;

import com.gungorefe.simpleportfolio.exception.image.ImageNotFoundException;
import com.gungorefe.simpleportfolio.exception.image.MalformedImageMimeTypeException;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageMimeTypeException;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageNameException;
import com.gungorefe.simpleportfolio.exception.page.ComponentNotFoundException;
import com.gungorefe.simpleportfolio.exception.page.InvalidLocaleException;
import com.gungorefe.simpleportfolio.exception.page.PageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(InvalidLocaleException.class)
    public ResponseEntity<?> invalidLocaleException(InvalidLocaleException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<?> pageNotFoundException(PageNotFoundException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ComponentNotFoundException.class)
    public ResponseEntity<?> componentNotFoundException(ComponentNotFoundException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<?> imageNotFoundException(ImageNotFoundException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException(BadCredentialsException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.FORBIDDEN
        );
    }
}
