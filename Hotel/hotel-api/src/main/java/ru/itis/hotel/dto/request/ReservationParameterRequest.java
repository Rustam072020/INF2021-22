package ru.itis.hotel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.hotel.enums.ApartmentLevel;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationParameterRequest {

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Integer minCost;

    private Integer maxCost;

    private ApartmentLevel apartmentLevel;
}
