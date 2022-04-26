package ru.itis.hotel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentRequest {

    @NotBlank
    private Integer apartmentNumber;
}
