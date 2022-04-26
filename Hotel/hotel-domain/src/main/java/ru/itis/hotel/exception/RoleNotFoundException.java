package ru.itis.hotel.exception;

public class RoleNotFoundException extends RusappNotFoundException {

    public RoleNotFoundException() {
        super("Role not found");
    }
}
