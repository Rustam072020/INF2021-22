package ru.itis.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hotel.api.AdministratorApi;
import ru.itis.hotel.dto.request.ApartmentRequest;
import ru.itis.hotel.dto.request.HotelRequest;
import ru.itis.hotel.dto.response.ApartmentResponse;
import ru.itis.hotel.dto.response.HotelResponse;
import ru.itis.hotel.enums.ApartmentState;
import ru.itis.hotel.service.AdministratorService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AdministratorController implements AdministratorApi {

    private final AdministratorService administratorService;

    @Override
    public ApartmentResponse addNewApartment(UUID hotelId, ApartmentRequest request) {
        return administratorService.addNewApartment(hotelId, request);
    }

    @Override
    public HotelResponse registrationHotel(HotelRequest hotel) {

        return administratorService.registrationHotel(hotel);
    }

    @Override
    public void changeApartmentState(UUID apartmentId, ApartmentState state) {
        administratorService.changeApartmentState(apartmentId, state);
    }

    @Override
    public UUID addNewAdministrator(UUID administratorId, UUID hotelId) {

        return administratorService.addNewAdministrator(administratorId, hotelId);
    }
}
