package ru.itis.hotel.security.jwt.provider;

import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.enums.Role;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.Map;

public interface JwtAccessTokenProvider {

    String generateAccessToken(String subject, Map<String, Object> data);

    boolean validateAccessToken(String accessToken, String subject);

    UserResponse userInfoByToken(String token);

    Claims parseAccessToken(String accessToken);

    String getRoleFromAccessToken(String accessToken);

    Date getExpirationDateFromAccessToken(String accessToken);

    String getSubjectFromAccessToken(String accessToken);

}
