package ru.itis.hotel.exception;

import org.springframework.http.HttpStatus;

public class RusappAlreadyExistException extends RusappServiceException{

    public RusappAlreadyExistException(String message) {
        super(HttpStatus.ALREADY_REPORTED, message);
    }
}
