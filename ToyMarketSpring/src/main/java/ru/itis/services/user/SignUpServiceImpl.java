package ru.itis.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignUpForm;
import ru.itis.models.User;
import ru.itis.repository.UserRepository;
import ru.itis.repository.UserRepositoryJdbсImpl;

@Service
public class SignUpServiceImpl implements SignUpService {

    UserRepository userRepository;

    @Autowired
    public SignUpServiceImpl(UserRepositoryJdbсImpl userRepositoryJdbs) {
        this.userRepository = userRepositoryJdbs;
    }

    @Override
    public void signUp(SignUpForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .name(form.getName())
                .password(form.getPassword())
                .lastName(form.getLastName())
                .avatar(form.getAvatar())
                .build();
        userRepository.saveUser(user);
    }

    @Override
    public boolean isNewUser(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
