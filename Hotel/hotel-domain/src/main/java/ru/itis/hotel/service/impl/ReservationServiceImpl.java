package ru.itis.hotel.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.hotel.dto.request.ReservationParameterRequest;
import ru.itis.hotel.dto.request.ReservationRequest;
import ru.itis.hotel.dto.response.FreeApartmentResponse;
import ru.itis.hotel.dto.response.ReservationResponse;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.enums.ApartmentState;
import ru.itis.hotel.exception.reservation.ReservationForbiddenException;
import ru.itis.hotel.exception.reservation.ReservationNotFoundException;
import ru.itis.hotel.mapper.ApartmentMapper;
import ru.itis.hotel.mapper.ReservationMapper;
import ru.itis.hotel.model.ApartmentEntity;
import ru.itis.hotel.model.ReservationEntity;
import ru.itis.hotel.repository.ApartmentRepository;
import ru.itis.hotel.repository.ReservationRepository;
import ru.itis.hotel.service.ReservationService;
import ru.itis.hotel.util.EmailUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;
    private final ReservationMapper reservationMapper;
    private final EmailUtil emailUtil;

    @Value("${name.reservation.template}")
    private String emailTemplaName;

    @Value("${shop.default-page-size}")
    private int size;

    @Override
    public ReservationResponse toBookApartment(ReservationRequest reservationRequest) {
        ApartmentEntity apartment = Optional.of(apartmentRepository
                .getById(reservationRequest.getApartmentId()))
                .orElseThrow();
        UserResponse user = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        toSendConfirmLetterEmail(user);

        return ReservationResponse.builder()
                        .uuid(
                reservationRepository.save(
                ReservationEntity.builder()
                        .apartment(apartment)
                        .checkInDate(reservationRequest.getCheckInDate())
                        .checkOutDate(reservationRequest.getCheckOutDate())
                        .client(user.getUuid())
                        .build()
        ).getUuid())
                .build();
    }

    @Override
    public void toCancelReservation(UUID reservationId) {

        UserResponse user = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
        if (user.getUuid() != reservation.getClient()){
            throw new ReservationForbiddenException();
        }
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public List<FreeApartmentResponse> getFreeApartments(ReservationParameterRequest params, int page) {

        List<UUID> reservations = reservationRepository.searchBookedApartmentsAtSpecificTime(params.getCheckInDate(), params.getCheckOutDate());
        if (reservations.size()==0) {
            reservations.add(UUID.randomUUID());
        }
        PageRequest request = PageRequest.of(page, size);
        getCorrectParameters(params);
        Page<ApartmentEntity> result;
        if (params.getApartmentLevel() == null) {
            result = apartmentRepository.findAllByUuidIsNotInAndCostBetweenAndStateEquals(
                    reservations,
                    params.getMinCost(),
                    params.getMaxCost(),
                    ApartmentState.CAN_SETTLED,
                    request
            );
        } else {
            result = apartmentRepository.findAllByUuidNotInAndLevelEqualsAndCostBetweenAndStateEquals(
                    reservations,
                    params.getApartmentLevel(),
                    params.getMinCost(),
                    params.getMaxCost(),
                    ApartmentState.CAN_SETTLED,
                    request
            );
        }
        return apartmentMapper.toFreeApartmentsResponseList(result.getContent());
    }

    @Override
    public List<ReservationResponse> getUserReservations() {
        UserResponse user = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return reservationMapper.toReservationResponseList(reservationRepository.findByClient(user.getUuid()));
    }

    @Override
    public ReservationResponse getUserReservation(UUID reservationId) {
        UserResponse user = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
        if (!user.getUuid().equals(reservation.getClient())){
            throw new ReservationForbiddenException();
        }
        return reservationMapper.toReservationResponse(reservation);
    }

    private ReservationParameterRequest getCorrectParameters(ReservationParameterRequest parameters) {
        if (parameters.getMinCost() == null){
            parameters.setMinCost(0);
        }
        if (parameters.getMaxCost() == null) {
            parameters.setMaxCost(1000000);
        }
        return parameters;
    }

    private void toSendConfirmLetterEmail(UserResponse user) {
        Runnable task = () -> {
            HashMap<String, String> data = new HashMap<>();
            data.put("confirm_code", String.valueOf(user.getUuid()));
            data.put("name", user.getFullName());
            emailUtil.sendMail(user.getEmail(), "confirm", emailTemplaName, data);
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
