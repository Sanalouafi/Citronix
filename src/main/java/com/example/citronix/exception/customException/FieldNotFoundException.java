package com.example.citronix.exception.customException;

public class FieldNotFoundException extends RuntimeException {

    public FieldNotFoundException(Long fieldId) {
        super("Field with ID " + fieldId + " not found.");
    }
}
