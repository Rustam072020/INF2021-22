package ru.itis.hotel.exception.apartment;

import ru.itis.hotel.exception.RusappNotFoundException;

public class ApartmentNotFoundException extends RusappNotFoundException {
    public ApartmentNotFoundException() {
        super("Apartment not found");
    }
}
