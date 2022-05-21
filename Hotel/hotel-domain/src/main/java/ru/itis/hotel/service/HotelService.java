package ru.itis.hotel.service;

import ru.itis.hotel.dto.request.HotelRequest;
import ru.itis.hotel.dto.response.HotelResponse;

import java.util.UUID;

public interface HotelService {

    HotelResponse getHotel(UUID hotelId);
}
