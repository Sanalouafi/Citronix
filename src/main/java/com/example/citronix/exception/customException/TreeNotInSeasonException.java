package com.example.citronix.exception.customException;

public class TreeNotInSeasonException extends RuntimeException {

    public TreeNotInSeasonException(String message) {
        super(message);
    }
}
