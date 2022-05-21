package ru.itis.hotel.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itis.hotel.dto.jwt.TokenCoupleResponse;
import ru.itis.hotel.dto.request.CreateUserRequest;
import ru.itis.hotel.dto.request.UserRequest;
import ru.itis.hotel.dto.response.UserResponse;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/users")
public interface UserApi {

    @PostMapping(value = "/signUp", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createUser(@RequestBody CreateUserRequest user);

    @GetMapping(value = "/{user-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserResponse getUser(@PathVariable("user-id") UUID userId);

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse login(@RequestBody UserRequest userRequest);

    @PutMapping(value = "/update", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserResponse updateUser(@RequestBody CreateUserRequest updateUser);

}
