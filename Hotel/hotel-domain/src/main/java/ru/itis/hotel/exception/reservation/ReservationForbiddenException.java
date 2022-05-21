package ru.itis.hotel.exception.reservation;

import org.springframework.http.HttpStatus;
import ru.itis.hotel.exception.RusappForbiddenException;

public class ReservationForbiddenException extends RusappForbiddenException {

    public ReservationForbiddenException() {
        super("This reservation forbidden");
    }
}
