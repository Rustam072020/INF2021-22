package ru.itis.hotel.exception;

import org.springframework.http.HttpStatus;

public class RusappForbiddenException extends RusappServiceException{

    public RusappForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
