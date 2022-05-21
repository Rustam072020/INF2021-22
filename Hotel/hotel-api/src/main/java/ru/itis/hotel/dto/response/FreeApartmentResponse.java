package ru.itis.hotel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.hotel.enums.ApartmentLevel;
import ru.itis.hotel.enums.ApartmentState;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;


@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeApartmentResponse {

    private UUID uuid;
    private Integer apartmentNumber;
    private Integer cost;
    private ApartmentState state;
    private ApartmentLevel level;
}
