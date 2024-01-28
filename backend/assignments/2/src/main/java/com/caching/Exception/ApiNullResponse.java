package com.caching.Exception;

public class ApiNullResponse extends RuntimeException{
    public ApiNullResponse(String message){
        super(message);
    }
}
