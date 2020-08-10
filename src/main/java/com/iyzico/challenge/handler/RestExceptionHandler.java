package com.iyzico.challenge.handler;

import com.iyzico.challenge.exception.BaseException;
import com.iyzico.challenge.exception.BaseValidationException;
import com.iyzico.challenge.exception.ExceptionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class RestExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> handleIllegalArgument(BaseException ex, Locale locale) {

        String errorMessage = messageSource.getMessage(ex.getMessage(), null, locale);

        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
    }

    @ExceptionHandler(BaseValidationException.class)
    public ResponseEntity<String> validationException(BaseValidationException ex, Locale locale) {

        String errorMessage = messageSource.getMessage(ex.getMessage(), null, locale);
        return new ResponseEntity<>(errorMessage, HttpStatus.OK);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex, Locale locale) {
        return new ResponseEntity<>(ExceptionCode.UNEXPECTED_ERROR, HttpStatus.OK);
    }
}
