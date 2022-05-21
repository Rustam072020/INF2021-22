package ru.itis.hotel.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.hotel.dto.request.ApartmentRequest;
import ru.itis.hotel.dto.request.ReviewRequest;
import ru.itis.hotel.dto.response.ApartmentResponse;
import ru.itis.hotel.dto.response.ReviewResponse;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/apartment")
public interface ApartmentApi {

    @PostMapping(value = "/setReview/{apartment-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UUID setApartmentReview(@PathVariable("apartment-id") UUID apartmentId, @RequestBody ReviewRequest request);

    @GetMapping(value = "/getOne/{apartment-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ApartmentResponse getApartment(@PathVariable("apartment-id") UUID apartmentId);

}
