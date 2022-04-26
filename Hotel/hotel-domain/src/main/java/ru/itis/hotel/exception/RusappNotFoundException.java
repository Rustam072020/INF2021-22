package ru.itis.hotel.exception;

import org.springframework.http.HttpStatus;

public class RusappNotFoundException extends RusappServiceException {

    public RusappNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}

