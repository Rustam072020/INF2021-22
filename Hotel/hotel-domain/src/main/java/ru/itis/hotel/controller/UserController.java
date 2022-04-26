package ru.itis.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hotel.api.UserApi;
import ru.itis.hotel.dto.jwt.TokenCoupleResponse;
import ru.itis.hotel.dto.request.CreateUserRequest;
import ru.itis.hotel.dto.request.UserRequest;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.security.jwt.service.JwtTokenService;
import ru.itis.hotel.service.UserService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @Override
    public UUID createUser(@Valid CreateUserRequest user) {
        return userService.signUp(user);
    }

    @Override
    public UserResponse getUser(UUID userId) {
        return userService.getUserById(userId);
    }

    @Override
    public TokenCoupleResponse login(UserRequest userRequest) {
        return jwtTokenService.generateTokenCouple(userService.login(userRequest));
    }
}
