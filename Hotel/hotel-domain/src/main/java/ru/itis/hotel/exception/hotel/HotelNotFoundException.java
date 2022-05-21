package ru.itis.hotel.exception.hotel;

import ru.itis.hotel.exception.RusappNotFoundException;

public class HotelNotFoundException extends RusappNotFoundException {

    public HotelNotFoundException() {
        super("Hotel not found");
    }
}
