package com.javaoktato.blog.controller;

import com.javaoktato.blog.dto.SystemMessage;
import com.javaoktato.blog.dto.ValidationMessage;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<List<ValidationMessage>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ValidationMessage> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ValidationMessage::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<List<ValidationMessage>> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<SystemMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(new SystemMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}