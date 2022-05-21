package ru.itis.hotel.api;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.hotel.dto.request.HotelRequest;
import ru.itis.hotel.dto.response.HotelResponse;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/hotel")
public interface HotelApi {

    @GetMapping(value = "/getOne", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    HotelResponse getHotel(@PathVariable("hotel-id")UUID hotelId);

}
