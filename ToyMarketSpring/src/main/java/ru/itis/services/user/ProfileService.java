package ru.itis.services.user;

import ru.itis.models.User;

public interface ProfileService {
    User getUser(String email);
}
