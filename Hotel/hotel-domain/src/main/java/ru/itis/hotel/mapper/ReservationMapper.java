package ru.itis.hotel.mapper;

import org.mapstruct.Mapper;
import ru.itis.hotel.dto.response.ReservationResponse;
import ru.itis.hotel.model.ReservationEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationResponse toReservationResponse(ReservationEntity reservationEntity);
    List<ReservationResponse> toReservationResponseList(List<ReservationEntity> reservationEntities);
}
