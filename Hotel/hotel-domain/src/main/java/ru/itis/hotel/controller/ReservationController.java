package ru.itis.hotel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.hotel.api.ReservationApi;
import ru.itis.hotel.dto.request.ReservationParameterRequest;
import ru.itis.hotel.dto.request.ReservationRequest;
import ru.itis.hotel.dto.response.FreeApartmentResponse;
import ru.itis.hotel.dto.response.ReservationResponse;
import ru.itis.hotel.service.ReservationService;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReservationController implements ReservationApi {

    private final ReservationService reservationService;

    @Override
    public ReservationResponse toBookApartment(ReservationRequest reservationRequest) {
        return reservationService.toBookApartment(reservationRequest);
    }

    @Override
    public void toCancelReservation(UUID reservationId) {
        reservationService.toCancelReservation(reservationId);
    }

    @Override
    public List<ReservationResponse> getUserReservations() {
        return reservationService.getUserReservations();
    }

    @Override
    public ReservationResponse getUserReservation(UUID reservationId) {
        return reservationService.getUserReservation(reservationId);
    }

    @Override
    public List<FreeApartmentResponse> getFreeApartment(int page, ReservationParameterRequest reservationParameterRequest){
        return reservationService.getFreeApartments(reservationParameterRequest ,page);
    }
}
