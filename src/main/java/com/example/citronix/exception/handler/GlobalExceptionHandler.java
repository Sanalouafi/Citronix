package com.example.citronix.exception.handler;

import com.example.citronix.exception.customException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FarmNotFoundException.class)
    public ResponseEntity<String> handleFarmNotFoundException(FarmNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFieldAreaException.class)
    public ResponseEntity<String> handleInvalidFieldAreaException(InvalidFieldAreaException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<String> handleFieldNotFoundException(FieldNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TreeNotInSeasonException.class)
    public ResponseEntity<String> handleTreeNotInSeasonException(TreeNotInSeasonException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HarvestLimitExceededException.class)
    public ResponseEntity<String> handleHarvestLimitExceededException(HarvestLimitExceededException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<String> handleSaleNotFoundException(SaleNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    
}
