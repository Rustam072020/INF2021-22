package ru.itis.hotel.api;

import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/admin")
public interface AdminApi {

    @DeleteMapping(value = "/user/baned/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    void toBanUser(@PathVariable("user-id") UUID userId);

    @DeleteMapping(value = "/review/delete/{comment-id}")
    @ResponseStatus(HttpStatus.OK)
    void removeReview(@PathVariable("comment-id") UUID reviewId);
}
