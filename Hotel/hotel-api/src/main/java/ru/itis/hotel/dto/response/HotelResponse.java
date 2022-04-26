package ru.itis.hotel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {

    private UUID uuid;
    private String title;
    private Double rating;
    private List<FileResponse> files = new ArrayList<>();
}
