package com.reonfernandes.Quiz_App.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<String> QuestionNotFoundException(QuestionNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> CategoryNotFoundException(CategoryNotFoundException categoryException){
        return new ResponseEntity<>(categoryException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> IdNotFoundException(IdNotFoundException idException){
        return new ResponseEntity<>(idException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
