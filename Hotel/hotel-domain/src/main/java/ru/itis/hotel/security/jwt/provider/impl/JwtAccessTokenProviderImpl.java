package ru.itis.hotel.security.jwt.provider.impl;

import io.jsonwebtoken.Claims;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.exception.AuthenticationHeaderException;
import ru.itis.hotel.security.jwt.provider.JwtAccessTokenProvider;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.hotel.service.UserService;

import java.time.Instant;
import java.util.HashMap;

import static ru.itis.hotel.consts.HotelConstant.ROLE;

@Component
@RequiredArgsConstructor
public class JwtAccessTokenProviderImpl implements JwtAccessTokenProvider {

    private final UserService userService;

    @Value("${jwt.expiration.access.mills}")
    private long expirationAccessInMills;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public String generateAccessToken(String subject, Map<String, Object> data) {

        Map<String, Object> claims = new HashMap<>(data);
        claims.put(Claims.SUBJECT, subject);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(expirationAccessInMills)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    @Override
    public boolean validateAccessToken(String accessToken, String subject) {
        try {
            Claims claims = parseAccessToken(accessToken);
            String subjectFromToken = claims.getSubject();
            Date date = claims.getExpiration();
            System.out.println(date);
            System.out.println(new Date());
            return subject.equals(subjectFromToken) && date.after(new Date());
        } catch (ExpiredJwtException e) {
            throw new AuthenticationHeaderException("Token expired date error");
        }
    }

    @Override
    public UserResponse userInfoByToken(String token) {
        try {
            Claims claims = parseAccessToken(token);
            String role = getRoleFromAccessToken(token);
            String subject = claims.getSubject();
            UserResponse user = (UserResponse) userService.findBySubject(subject)
                    .orElseThrow(() -> new AuthenticationHeaderException("User with this name was not found"));
            return user;
        } catch (ExpiredJwtException e) {
            throw new AuthenticationHeaderException("Token expired date error");
        }
    }

    @Override
    public Claims parseAccessToken(String accessToken) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(accessToken).getBody();
    }

    @Override
    public String getRoleFromAccessToken(String accessToken) {
        try {
            return  String.valueOf(Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(accessToken).getBody().get(ROLE));
        } catch (ExpiredJwtException e) {
            return String.valueOf(e.getClaims().get(ROLE));
        }
    }

    @Override
    public Date getExpirationDateFromAccessToken(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(accessToken)
                    .getBody().getExpiration();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getExpiration();
        }
    }

    @Override
    public String getSubjectFromAccessToken(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(accessToken).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

}
