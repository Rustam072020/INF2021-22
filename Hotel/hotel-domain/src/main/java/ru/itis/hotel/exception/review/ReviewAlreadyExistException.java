package ru.itis.hotel.exception.review;

import ru.itis.hotel.exception.RusappAlreadyExistException;

public class ReviewAlreadyExistException extends RusappAlreadyExistException {

    public ReviewAlreadyExistException() {
        super("Review already exists");
    }
}
