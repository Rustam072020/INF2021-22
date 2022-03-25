package ru.itis.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignInForm;
import ru.itis.models.User;
import ru.itis.repository.UserRepository;
import ru.itis.repository.UserRepositoryJdbсImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    private final static String PAGE_PERSON_COOKIE_NAME = "pagePerson";

    private final static int PERSON_COOKIE_MAX_AGE = 86400;

    UserRepository crudRepository;

    @Autowired
    public SignInServiceImpl(UserRepositoryJdbсImpl crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public boolean correctSignIn(SignInForm form){
        Optional<User> users = crudRepository.findByEmail(form.getEmail());
        return users.isPresent() && users.get().getPassword().equals(form.getPassword());
    }

    @Override
    public void createCookie(HttpServletResponse response, String login){
        Cookie cookie = new Cookie(PAGE_PERSON_COOKIE_NAME, login);
        cookie.setMaxAge(PERSON_COOKIE_MAX_AGE);
        response.addCookie(cookie);
    }
}
