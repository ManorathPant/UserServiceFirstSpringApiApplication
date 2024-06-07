package com.scaler.userservicefirstspringapi.exceptions;

public class UserNotFoundException extends RuntimeException {
    public  UserNotFoundException(String message){
        super(message);
    }

}
