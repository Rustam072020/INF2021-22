package ru.itis.hotel.security.jwt.provider;

import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.model.RefreshTokenEntity;

public interface JwtRefreshTokenProvider {

    String generateRefreshToken(UserResponse accountResponse);

    RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken);


}
