package ru.itis.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hotel.api.HotelApi;
import ru.itis.hotel.dto.request.HotelRequest;
import ru.itis.hotel.dto.response.HotelResponse;
import ru.itis.hotel.service.HotelService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class HotelController implements HotelApi {

    private final HotelService hotelService;

    @Override
    public HotelResponse getHotel(UUID hotelId) {
        return hotelService.getHotel(hotelId);
    }
}
