package com.example.citronix.exception.customException;

public class InvalidFieldAreaException extends RuntimeException{
    public InvalidFieldAreaException() {
        super("The field's area exceeds the allowed limits.");
    }
}
