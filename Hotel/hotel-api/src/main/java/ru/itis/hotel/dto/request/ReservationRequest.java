package ru.itis.hotel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    private UUID apartmentId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;
}
