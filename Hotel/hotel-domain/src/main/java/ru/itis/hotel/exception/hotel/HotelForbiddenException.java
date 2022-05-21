package ru.itis.hotel.exception.hotel;

import ru.itis.hotel.exception.RusappForbiddenException;

public class HotelForbiddenException extends RusappForbiddenException {

    public HotelForbiddenException() {
        super("This hotel forbidden");
    }
}
