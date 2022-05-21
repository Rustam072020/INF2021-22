package ru.itis.hotel.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.hotel.dto.request.ApartmentRequest;
import ru.itis.hotel.dto.request.HotelRequest;
import ru.itis.hotel.dto.response.ApartmentResponse;
import ru.itis.hotel.dto.response.HotelResponse;
import ru.itis.hotel.enums.ApartmentState;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/administrator")
public interface AdministratorApi {

    @GetMapping(value = "/changeApartmentState/{apartment-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void changeApartmentState(@PathVariable("apartment-id") UUID apartmentId, @RequestParam("state") ApartmentState state);

    @GetMapping(value = "/addNewAdministrator/{administrator-id}/{hotel-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UUID addNewAdministrator(@PathVariable("administrator-id") UUID administratorId, @PathVariable("hotel-id") UUID hotelId);

    @PostMapping(value = "/addNewApartment/{hotel-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ApartmentResponse addNewApartment(@PathVariable("hotel-id") UUID hotelId, @RequestBody ApartmentRequest request);

    @PostMapping(value = "/registrationHotel", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    HotelResponse registrationHotel(@RequestBody HotelRequest hotel);

}
