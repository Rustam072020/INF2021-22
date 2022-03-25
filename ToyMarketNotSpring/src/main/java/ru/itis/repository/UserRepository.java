package ru.itis.repository;

import ru.itis.models.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    Optional<User> findPassByEmail(String email);

    void saveUser(User user);

    void updatePass(String email, String password);


    void updatePhoto(String email, String photo);
}
