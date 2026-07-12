package com.tidyup.StockService.infrastruture.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityDoesNotExistException extends RuntimeException {

    public EntityDoesNotExistException(String message) {
        super(message);
    }
}
