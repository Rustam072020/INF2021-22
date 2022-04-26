package ru.itis.hotel.security.jwt.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.exception.TokenRefreshException;
import ru.itis.hotel.exception.UserNotFoundException;
import ru.itis.hotel.model.RefreshTokenEntity;
import ru.itis.hotel.model.UserRefreshTokenEntity;
import ru.itis.hotel.repository.UserRefreshTokenRepository;
import ru.itis.hotel.repository.UserRepository;
import ru.itis.hotel.security.jwt.provider.JwtRefreshTokenProvider;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtRefreshTokenProviderImpl implements JwtRefreshTokenProvider {

    @Value("${jwt.expiration.access.mills}")
    private Long expirationRefreshInMills;

    private final UserRefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public String generateRefreshToken(UserResponse accountResponse) {
        UserRefreshTokenEntity refreshToken = UserRefreshTokenEntity.builder()
                .account(userRepository
                        .findOneByEmail(accountResponse.getEmail())
                        .orElseThrow(UserNotFoundException::new))
                .expiryDate(Instant.now().plusMillis(expirationRefreshInMills))
                .build();
        refreshTokenRepository.save(refreshToken);
        return String.valueOf(refreshToken.getAccount().getUuid());
    }

    @Override
    public RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken) {
        return refreshTokenRepository.findById(UUID.fromString(refreshToken)).map(token -> {
            refreshTokenRepository.delete(token);
            if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
                throw new TokenRefreshException(String.valueOf(token.getUuid()), "Срок действия токена обновления истек.");
            }
            return refreshTokenRepository.save(
                    UserRefreshTokenEntity.builder()
                            .expiryDate(Instant.now().plusMillis(expirationRefreshInMills))
                            .account(token.getAccount())
                            .build());
        }).orElseThrow(() -> {
            throw new TokenRefreshException(refreshToken, "Токен не существует.");
        });
    }
}
