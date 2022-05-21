package ru.itis.hotel.exception.reservation;

import ru.itis.hotel.exception.RusappNotFoundException;

public class ReservationNotFoundException extends RusappNotFoundException {

    public ReservationNotFoundException() {
        super("Reservation not found");
    }
}

