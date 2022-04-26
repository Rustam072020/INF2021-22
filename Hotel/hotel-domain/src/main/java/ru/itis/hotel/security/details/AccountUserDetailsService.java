package ru.itis.hotel.security.details;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.exception.AuthenticationHeaderException;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) {
        return loadUserDetails((UserResponse) preAuthenticatedAuthenticationToken.getPrincipal(), String.valueOf(preAuthenticatedAuthenticationToken.getCredentials()));
    }

    private UserDetails loadUserDetails(UserResponse userResponse, String token) {
        try {
            return Optional.ofNullable(userResponse)
                    .map(account -> {
                        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+account.getRole());
                        return AccountUserDetails.builder()
                                .id(account.getUuid())
                                .username(account.getEmail())
                                .fullName(account.getFullName())
                                .createDate(null)
                                .authorities(Collections.singleton(authority))
                                .isAccountNonExpired(true)
                                .isCredentialsNonExpired(true)
                                .isAccountNonLocked(true)
                                .isEnabled(true)
                                .token(token)
                                .build();
                    })
                    .orElseThrow(() -> new UsernameNotFoundException("Unknown user by token " + token));
        } catch (Exception exception) {
            throw new AuthenticationHeaderException(exception.getMessage());
        }
    }

}


