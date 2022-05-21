package ru.itis.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hotel.api.ApartmentApi;
import ru.itis.hotel.dto.request.ApartmentRequest;
import ru.itis.hotel.dto.request.ReviewRequest;
import ru.itis.hotel.dto.response.ApartmentResponse;
import ru.itis.hotel.dto.response.ReviewResponse;
import ru.itis.hotel.service.ApartmentService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ApartmentController implements ApartmentApi {

    private final ApartmentService apartmentService;

    @Override
    public UUID setApartmentReview(UUID apartmentId, ReviewRequest request) {
        return apartmentService.setApartmentReview(apartmentId, request);
    }

    @Override
    public ApartmentResponse getApartment(UUID apartmentId) {
        return apartmentService.getApartment(apartmentId);
    }
}
