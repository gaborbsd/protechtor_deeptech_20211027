package com.javaoktato.blog.controller;

import com.javaoktato.blog.dto.ValidationMessage;
import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<ValidationMessage> errors;

    public ValidationException(List<ValidationMessage> errors) {
        this.errors = errors;
    }

    public List<ValidationMessage> getErrors() {
        return errors;
    }
}
