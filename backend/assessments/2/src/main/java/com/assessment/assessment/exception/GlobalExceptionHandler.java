package com.assessment.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserNotFoundException>handleCustomException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ProductNotFoundException> handleProductException(ProductNotFoundException productNotFoundException){
        return new ResponseEntity<>(productNotFoundException,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<InventoryNotFound> handleProductException(InventoryNotFound inventoryNotFound){
        return new ResponseEntity<>(inventoryNotFound,HttpStatus.NOT_FOUND);
    }
}
