package ru.itis.hotel.service;

import ru.itis.hotel.dto.request.ApartmentRequest;
import ru.itis.hotel.dto.request.HotelRequest;
import ru.itis.hotel.dto.response.ApartmentResponse;
import ru.itis.hotel.dto.response.HotelResponse;
import ru.itis.hotel.enums.ApartmentState;

import java.util.UUID;

public interface AdministratorService {

    ApartmentResponse addNewApartment(UUID hotelId, ApartmentRequest request);

    UUID addNewAdministrator(UUID administratorId, UUID hotelId);

    HotelResponse registrationHotel(HotelRequest hotel);

    void changeApartmentState(UUID apartmentId, ApartmentState state);
}
