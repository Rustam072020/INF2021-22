package ru.itis.hotel.exception.user;

import ru.itis.hotel.exception.RusappNotFoundException;

public class UserNotFoundException extends RusappNotFoundException {

    public UserNotFoundException() {
        super("User not found");
    }
}

