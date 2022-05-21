package ru.itis.hotel.exception.user;

import org.springframework.http.HttpStatus;
import ru.itis.hotel.exception.RusappServiceException;

public class TokenRefreshException extends RusappServiceException {

    public TokenRefreshException(String token, String message) {
        super(HttpStatus.UNAUTHORIZED, String.format("Failed for [%s]: %s", token, message));
    }
}
