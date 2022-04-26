package ru.itis.hotel.exception;

public class UserNotFoundException extends RusappNotFoundException {

    public UserNotFoundException() {
        super("User not found");
    }
}

