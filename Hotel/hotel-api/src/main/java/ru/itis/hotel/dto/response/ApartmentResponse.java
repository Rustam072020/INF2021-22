package ru.itis.hotel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.hotel.enums.ApartmentState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentResponse {

    private UUID uuid;
    private Integer apartmentNumber;
    private Integer cost;
    private String state;
    private List<FileResponse> files = new ArrayList<>();
}
