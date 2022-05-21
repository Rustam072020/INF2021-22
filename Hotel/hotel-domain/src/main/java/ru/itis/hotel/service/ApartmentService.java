package ru.itis.hotel.service;

import ru.itis.hotel.dto.request.ApartmentRequest;
import ru.itis.hotel.dto.request.ReviewRequest;
import ru.itis.hotel.dto.response.ApartmentResponse;

import java.util.UUID;

public interface ApartmentService {

    UUID setApartmentReview(UUID apartmentId, ReviewRequest request);

    ApartmentResponse getApartment(UUID apartmentId);
}
