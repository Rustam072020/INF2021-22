package ru.itis.hotel.exception.user;

import ru.itis.hotel.exception.RusappNotFoundException;

public class RoleNotFoundException extends RusappNotFoundException {

    public RoleNotFoundException() {
        super("Role not found");
    }
}
