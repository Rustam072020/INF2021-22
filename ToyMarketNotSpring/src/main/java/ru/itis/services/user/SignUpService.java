package ru.itis.services.user;

import ru.itis.dto.SignUpForm;

public interface SignUpService {
    void signUp(SignUpForm form);

    boolean isNewUser(String email);
}
