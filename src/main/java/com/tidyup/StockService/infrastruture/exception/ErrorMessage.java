package com.tidyup.StockService.infrastruture.exception;

import org.springframework.validation.FieldError;

public record ErrorMessage(String field, String message) {
    public ErrorMessage(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
