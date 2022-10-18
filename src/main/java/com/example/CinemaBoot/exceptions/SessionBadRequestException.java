package com.example.CinemaBoot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SessionBadRequestException extends RuntimeException {

    public SessionBadRequestException(String cause) {
        super(cause);
    }

}
