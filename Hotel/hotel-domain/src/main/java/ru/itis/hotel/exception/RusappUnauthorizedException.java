package ru.itis.hotel.exception;

import org.springframework.http.HttpStatus;

public class RusappUnauthorizedException extends RusappServiceException {

    public RusappUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
