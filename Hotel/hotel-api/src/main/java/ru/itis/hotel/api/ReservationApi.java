package ru.itis.hotel.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.hotel.dto.request.ReservationParameterRequest;
import ru.itis.hotel.dto.request.ReservationRequest;
import ru.itis.hotel.dto.response.FreeApartmentResponse;
import ru.itis.hotel.dto.response.ReservationResponse;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/reservation")
public interface ReservationApi {

    @GetMapping(value = "/myReservations", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<ReservationResponse> getUserReservations();

    @GetMapping(value = "/getOne/{reservation-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ReservationResponse getUserReservation(@PathVariable("reservation-id") UUID reservationId);

    @PostMapping(value = "/book", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ReservationResponse toBookApartment(@RequestBody ReservationRequest reservationRequest);

    @PostMapping(value = "/freeApartments", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<FreeApartmentResponse> getFreeApartment(@RequestParam("page") int page, @RequestBody ReservationParameterRequest reservationParameterRequest);

    @DeleteMapping(value = "/cancel/{reservation-id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void toCancelReservation(@PathVariable("reservation-id") UUID reservationId);


}
