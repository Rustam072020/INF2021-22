package ru.itis.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.User;
import ru.itis.repository.UserRepository;
import ru.itis.repository.UserRepositoryJdbсImpl;

@Service
public class ProfileServiceImpl implements ProfileService {

    UserRepository userRepository;

    @Autowired
    public ProfileServiceImpl(UserRepositoryJdbсImpl userRepositoryJdbsImpl) {
        this.userRepository =userRepositoryJdbsImpl;
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
    }

}
