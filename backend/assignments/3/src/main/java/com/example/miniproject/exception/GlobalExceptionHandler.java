package com.example.miniproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<HouseNotFoundException> handleCustomException(HouseNotFoundException houseNotFoundException){
        return new ResponseEntity<>(houseNotFoundException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<UserNotFoundException> handleCustomException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<RoomNotFoundException> handleCustomException(RoomNotFoundException roomNotFoundException){
        return new ResponseEntity<>(roomNotFoundException, HttpStatus.BAD_REQUEST);
    }
}