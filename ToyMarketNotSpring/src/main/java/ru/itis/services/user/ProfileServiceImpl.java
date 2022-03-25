package ru.itis.services.user;

import ru.itis.models.User;
import ru.itis.repository.UserRepository;

public class ProfileServiceImpl implements ProfileService {

    UserRepository userRepository;

    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository =userRepository;
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
    }

}
