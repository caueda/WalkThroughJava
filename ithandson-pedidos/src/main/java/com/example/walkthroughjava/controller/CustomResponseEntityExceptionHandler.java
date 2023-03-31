package com.example.walkthroughjava.controller;

import com.example.walkthroughjava.domain.ExceptionResponse;
import com.example.walkthroughjava.exception.SistemaException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SistemaException.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .field(((SistemaException) ex).campo)
                        .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllIntegrityConstraintViolationException(Exception ex, WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .field(((SistemaException) ex).campo)
                        .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
