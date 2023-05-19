package com.Bagbuilder.RestAPI.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class DiscNotFoundException extends RuntimeException {
    public DiscNotFoundException(String message) {
        super(message);
    }
}
