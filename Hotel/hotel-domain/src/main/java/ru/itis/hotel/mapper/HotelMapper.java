package ru.itis.hotel.mapper;

import org.mapstruct.Mapper;
import ru.itis.hotel.dto.request.HotelRequest;
import ru.itis.hotel.dto.response.HotelResponse;
import ru.itis.hotel.model.HotelEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelEntity toHotelEntity(HotelRequest hotelRequest);

    HotelResponse toHotelResponse(HotelEntity hotelEntity);

    List<HotelResponse> toHotelResponseList(List<HotelEntity> hotelEntities);
}
