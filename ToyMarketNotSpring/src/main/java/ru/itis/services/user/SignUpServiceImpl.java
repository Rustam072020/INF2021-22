package ru.itis.services.user;

import ru.itis.dto.SignUpForm;
import ru.itis.models.User;
import ru.itis.repository.UserRepository;

public class SignUpServiceImpl implements SignUpService {

    UserRepository userRepository;

    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
