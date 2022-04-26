package ru.itis.hotel.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RusappServiceException extends RuntimeException {
    private final HttpStatus httpStatus;

    public RusappServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

