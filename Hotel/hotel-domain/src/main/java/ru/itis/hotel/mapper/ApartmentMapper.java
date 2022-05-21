package ru.itis.hotel.mapper;

import org.mapstruct.Mapper;
import ru.itis.hotel.dto.request.ApartmentRequest;
import ru.itis.hotel.dto.response.ApartmentResponse;
import ru.itis.hotel.dto.response.FreeApartmentResponse;
import ru.itis.hotel.enums.ApartmentState;
import ru.itis.hotel.model.ApartmentEntity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {

    ApartmentEntity toApartmentEntity(ApartmentRequest apartmentRequest);

    ApartmentResponse toApartmentResponse(ApartmentEntity apartmentEntity);

    List<ApartmentEntity> toApartmentEntityList(List<ApartmentRequest> apartmentRequests);

    List<ApartmentResponse> toApartmentResponseList(List<ApartmentEntity> apartmentEntities);

    List<FreeApartmentResponse> toFreeApartmentsResponseList(List<ApartmentEntity> apartmentEntities);


}
