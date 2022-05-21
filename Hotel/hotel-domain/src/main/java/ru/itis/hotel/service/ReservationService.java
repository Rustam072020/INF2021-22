package ru.itis.hotel.service;

import ru.itis.hotel.dto.request.ApartmentRequest;
import ru.itis.hotel.dto.request.ReservationParameterRequest;
import ru.itis.hotel.dto.request.ReservationRequest;
import ru.itis.hotel.dto.response.FreeApartmentResponse;
import ru.itis.hotel.dto.response.ReservationResponse;
import ru.itis.hotel.model.ApartmentEntity;
import ru.itis.hotel.model.ReservationEntity;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    ReservationResponse toBookApartment(ReservationRequest reservationRequest);

    void toCancelReservation(UUID uuid);

    List<FreeApartmentResponse> getFreeApartments(ReservationParameterRequest parameters, int page);

    List<ReservationResponse> getUserReservations();

    ReservationResponse getUserReservation(UUID reservationId);
}
