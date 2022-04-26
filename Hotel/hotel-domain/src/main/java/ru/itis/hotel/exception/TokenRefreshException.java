package ru.itis.hotel.exception;

import org.springframework.http.HttpStatus;

public class TokenRefreshException extends RusappServiceException {

    public TokenRefreshException(String token, String message) {
        super(HttpStatus.UNAUTHORIZED, String.format("Failed for [%s]: %s", token, message));
    }
}
