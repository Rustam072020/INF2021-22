package ru.itis.hotel.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.security.jwt.service.JwtTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    private static final String BEARER = "Bearer";
    private final JwtTokenService jwtTokenService;

    public TokenAuthenticationFilter(JwtTokenService jwtTokenService, AuthenticationManager authenticationManager) {
        this.jwtTokenService = jwtTokenService;
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Optional<String> token = Optional.ofNullable(getTokenFromValidatedAuthorizationHeader(((HttpServletRequest) request).getHeader(AUTHORIZATION)));
            if (token.isPresent()) {
                UserResponse user = jwtTokenService.getUserInfoByToken(token.get());
                PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(user, token, Collections.singleton(new SimpleGrantedAuthority("ROLE_"+user.getRole())));

                if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else if (!SecurityContextHolder.getContext().getAuthentication().getCredentials().equals(token)) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            putExceptionInResponse(((HttpServletRequest) request), ((HttpServletResponse) response),
                    exception, HttpServletResponse.SC_UNAUTHORIZED);
        }
        chain.doFilter(request, response);
    }

    private String getTokenFromValidatedAuthorizationHeader(String authorizationHeader) {

        if (authorizationHeader == null) {
            return null;
        }

        log.info("Loading user for Authorization header: {}", authorizationHeader);

        if (!authorizationHeader.startsWith("Bearer")) {
            new IllegalArgumentException();
        }

        String token = getTokenFromAuthorizationHeader(authorizationHeader);
        if (token == null) {
            new IllegalArgumentException();
        }

        return token;
    }

    private String getTokenFromAuthorizationHeader(String authorizationHeader) {
        return  Optional.ofNullable(authorizationHeader)
                .map(bearer -> StringUtils.removeStart(bearer, BEARER).trim())
                .filter(StringUtils::isNotBlank)
                .orElse(null);
    }

    public static void putExceptionInResponse(HttpServletRequest request, HttpServletResponse response,
                                              Exception exception, int exceptionStatus) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(exceptionStatus);
        final Map<String, Object> body = new HashMap<>();
        body.put("status", exceptionStatus);
        body.put("error", "Unauthorized");
        body.put("message", exception.getMessage());
        body.put("path", request.getRequestURI());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }


}
