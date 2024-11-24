package com.example.citronix.exception.customException;

public class FarmNotFoundException extends RuntimeException {

    public FarmNotFoundException(Long farmId) {
        super("Farm with ID " + farmId + " not found.");
    }
}

