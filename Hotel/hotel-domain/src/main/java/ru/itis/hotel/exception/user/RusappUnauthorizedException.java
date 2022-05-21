package ru.itis.hotel.exception.user;

import org.springframework.http.HttpStatus;
import ru.itis.hotel.exception.RusappServiceException;

public class RusappUnauthorizedException extends RusappServiceException {

    public RusappUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
