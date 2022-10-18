package com.example.CinemaBoot.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String cause) {
        super(cause);
    }

}
