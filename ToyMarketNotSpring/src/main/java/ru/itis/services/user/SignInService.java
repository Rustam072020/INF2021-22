package ru.itis.services.user;

import ru.itis.dto.SignInForm;

import javax.servlet.http.HttpServletResponse;

public interface SignInService {
    boolean correctSignIn(SignInForm form);

    void createCookie(HttpServletResponse response, String login);
}
