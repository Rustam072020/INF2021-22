package ru.itis.hotel.security.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.itis.hotel.dto.jwt.TokenCoupleDto;
import ru.itis.hotel.dto.jwt.TokenCoupleResponse;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.model.RefreshTokenEntity;
import ru.itis.hotel.security.jwt.provider.JwtAccessTokenProvider;
import ru.itis.hotel.security.jwt.provider.JwtRefreshTokenProvider;
import ru.itis.hotel.security.jwt.service.JwtTokenService;

import java.util.Collections;

import static ru.itis.hotel.consts.HotelConstant.BEARER;
import static ru.itis.hotel.consts.HotelConstant.ROLE;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;
    private final JwtRefreshTokenProvider jwtRefreshTokenProvider;

    @Override
    public UserResponse getUserInfoByToken(String token) {
        return jwtAccessTokenProvider.userInfoByToken(token);
    }

    @Override
    public TokenCoupleResponse generateTokenCouple(UserResponse accountResponse) {
        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                accountResponse.getEmail(),
                Collections.singletonMap(ROLE, accountResponse.getRole())
        );
        String refreshToken = jwtRefreshTokenProvider.generateRefreshToken(accountResponse);
        return TokenCoupleResponse.builder()
                .accessToken(BEARER.concat(StringUtils.SPACE).concat(accessToken))
                .refreshToken(refreshToken)
                .accessTokenExpirationDate(jwtAccessTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }

    @Override
    public TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleDto) {
        String role = jwtAccessTokenProvider.getRoleFromAccessToken(tokenCoupleDto.getAccessToken().replace(BEARER.concat(StringUtils.SPACE), StringUtils.EMPTY));
        RefreshTokenEntity verifiedRefreshToken = jwtRefreshTokenProvider.verifyRefreshTokenExpiration(
                tokenCoupleDto.getRefreshToken());

        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                jwtAccessTokenProvider.getSubjectFromAccessToken(tokenCoupleDto.getAccessToken().replace(BEARER.concat(StringUtils.SPACE), StringUtils.EMPTY)),
                Collections.singletonMap(ROLE, role));
        return TokenCoupleResponse.builder()
                .refreshToken(String.valueOf(verifiedRefreshToken.getUuid()))
                .accessToken(BEARER.concat(StringUtils.SPACE).concat(accessToken))
                .accessTokenExpirationDate(jwtAccessTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();

    }

}
