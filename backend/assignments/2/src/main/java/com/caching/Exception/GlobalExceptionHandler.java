package com.caching.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ApiNullResponse.class})
    public ResponseEntity<Object> handleApiNullResponse(ApiNullResponse apiNullResponse){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiNullResponse.getMessage());
    }
}
