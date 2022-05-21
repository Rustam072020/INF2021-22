package ru.itis.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.hotel.dto.request.HotelRequest;
import ru.itis.hotel.dto.response.HotelResponse;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.enums.Role;
import ru.itis.hotel.exception.hotel.HotelForbiddenException;
import ru.itis.hotel.exception.hotel.HotelNotFoundException;
import ru.itis.hotel.exception.user.UserNotFoundException;
import ru.itis.hotel.mapper.HotelMapper;
import ru.itis.hotel.model.HotelEntity;
import ru.itis.hotel.model.UserEntity;
import ru.itis.hotel.repository.HotelRepository;
import ru.itis.hotel.repository.UserRepository;
import ru.itis.hotel.service.HotelService;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public HotelResponse getHotel(UUID hotelId) {

        return hotelMapper.toHotelResponse(hotelRepository.findById(hotelId)
                .orElseThrow(HotelNotFoundException::new));
    }
}
