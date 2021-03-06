package ru.itis.hotel.service;

import ru.itis.hotel.dto.request.CreateUserRequest;
import ru.itis.hotel.dto.request.UserRequest;
import ru.itis.hotel.dto.response.UserResponse;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<UserResponse> findBySubject(String subject);

    UserResponse login(UserRequest request);

    UUID signUp(CreateUserRequest user);

    UserResponse getUserById(UUID userId);

    @Transactional
    void confirmEmail(UUID userId);

    UserResponse updateUser(CreateUserRequest updateUser);
}
