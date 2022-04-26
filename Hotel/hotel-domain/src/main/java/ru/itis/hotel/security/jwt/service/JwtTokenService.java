package ru.itis.hotel.security.jwt.service;

import ru.itis.hotel.dto.jwt.TokenCoupleDto;
import ru.itis.hotel.dto.jwt.TokenCoupleResponse;
import ru.itis.hotel.dto.response.UserResponse;

public interface JwtTokenService {
    UserResponse getUserInfoByToken(String email);

    TokenCoupleResponse generateTokenCouple(UserResponse accountResponse);

    TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleDto);
}
