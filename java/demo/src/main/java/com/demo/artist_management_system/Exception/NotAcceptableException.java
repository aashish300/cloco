package com.demo.artist_management_system.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableException extends RuntimeException {
    public NotAcceptableException(String message) {
        super(message);
    }
}
