package ru.itis.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.hotel.dto.request.ApartmentRequest;
import ru.itis.hotel.dto.request.HotelRequest;
import ru.itis.hotel.dto.response.ApartmentResponse;
import ru.itis.hotel.dto.response.HotelResponse;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.enums.ApartmentState;
import ru.itis.hotel.enums.Role;
import ru.itis.hotel.exception.apartment.ApartmentNotFoundException;
import ru.itis.hotel.exception.hotel.HotelForbiddenException;
import ru.itis.hotel.exception.hotel.HotelNotFoundException;
import ru.itis.hotel.exception.user.UserNotFoundException;
import ru.itis.hotel.mapper.ApartmentMapper;
import ru.itis.hotel.mapper.HotelMapper;
import ru.itis.hotel.model.ApartmentEntity;
import ru.itis.hotel.model.HotelEntity;
import ru.itis.hotel.model.UserEntity;
import ru.itis.hotel.repository.ApartmentRepository;
import ru.itis.hotel.repository.HotelRepository;
import ru.itis.hotel.repository.UserRepository;
import ru.itis.hotel.service.AdministratorService;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepository;
    private final HotelMapper hotelMapper;
    private final ApartmentMapper apartmentMapper;


    @Override
    public HotelResponse registrationHotel(HotelRequest hotel) {

        UserResponse userResponse = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findById(userResponse.getUuid())
                .orElseThrow(UserNotFoundException::new);

        HotelEntity hotelEntity = hotelMapper.toHotelEntity(hotel);
        hotelEntity.setAdministrators(Set.of(user));

        return hotelMapper.toHotelResponse(hotelRepository.save(hotelEntity));
    }

    @Transactional
    @Override
    public void changeApartmentState(UUID apartmentId, ApartmentState state) {
        ApartmentEntity apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(ApartmentNotFoundException::new);
        HotelEntity hotel = getHotel(apartment.getHotel().getUuid());
        apartment.setState(state);
    }

    @Transactional
    @Override
    public UUID addNewAdministrator(UUID administratorId, UUID hotelId) {

        HotelEntity hotel = getHotel(hotelId);
        UserEntity newAdministrator = userRepository.findById(administratorId).orElseThrow(UserNotFoundException::new);
        if (newAdministrator.getRole() != Role.HOTEL_ADMINISTRATOR){
            throw new UserNotFoundException();
        }
        Set<UserEntity> admins = hotel.getAdministrators();
        admins.add(newAdministrator);
        hotel.setAdministrators(admins);

        return newAdministrator.getUuid();
    }



    @Override
    public ApartmentResponse addNewApartment(UUID hotelId, ApartmentRequest request) {
        HotelEntity hotel = getHotel(hotelId);
        ApartmentEntity apartment = apartmentMapper.toApartmentEntity(request);
        apartment.setHotel(hotel);
        return apartmentMapper.toApartmentResponse(apartmentRepository.save(apartment));
    }


    private HotelEntity getHotel(UUID hotelId) {
        UserResponse administrator = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HotelEntity hotel = hotelRepository.findById(hotelId)
                .orElseThrow(HotelNotFoundException::new);
        if (!userRepository.findById(administrator.getUuid()).get().getHotels().contains(hotel)){
            throw new HotelForbiddenException();
        }
        return hotel;
    }
}
