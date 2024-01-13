package org.example;
public class InvalidDataException extends RuntimeException{
        InvalidDataException(String message, Throwable cause){
            super(message, cause);
        }
}
