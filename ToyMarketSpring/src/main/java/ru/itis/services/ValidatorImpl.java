package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.dto.SignUpForm;
import ru.itis.repository.UserRepositoryJdbсImpl;
import ru.itis.services.validation.ErrorEntity;
import ru.itis.services.validation.Validator;

import java.util.Optional;

public class ValidatorImpl implements Validator {

    private UserRepositoryJdbсImpl userRepositoryJdbs;

    @Autowired
    public ValidatorImpl(UserRepositoryJdbсImpl userRepositoryJdbs){
        this.userRepositoryJdbs = userRepositoryJdbs;
    }

    @Override
    public Optional<ErrorEntity> validateRegistration(SignUpForm signUpForm) {
        if(signUpForm.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        } else if(userRepositoryJdbs.findByEmail(signUpForm.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }
        return Optional.empty();
    }

}
