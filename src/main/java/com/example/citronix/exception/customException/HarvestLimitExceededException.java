package com.example.citronix.exception.customException;

public class HarvestLimitExceededException extends RuntimeException {

    public HarvestLimitExceededException(String message) {
        super(message);
    }
}
