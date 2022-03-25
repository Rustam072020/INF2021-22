package ru.itis.services.user;


import ru.itis.dto.SignInForm;
import ru.itis.models.User;
import ru.itis.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    private final static String PAGE_PERSON_COOKIE_NAME = "pagePerson";

    private final static int PERSON_COOKIE_MAX_AGE = 86400;

    UserRepository userRepository;
    public SignInServiceImpl(UserRepository userRepository) {
        this.userRepository =userRepository;
    }

    @Override
    public boolean correctSignIn(SignInForm form){
        Optional<User> users = userRepository.findByEmail(form.getEmail());
        return users.isPresent() && users.get().getPassword().equals(form.getPassword());
    }

    @Override
    public void createCookie(HttpServletResponse response, String login){
        Cookie cookie = new Cookie(PAGE_PERSON_COOKIE_NAME, login);
        cookie.setMaxAge(PERSON_COOKIE_MAX_AGE);
        response.addCookie(cookie);
    }
}
