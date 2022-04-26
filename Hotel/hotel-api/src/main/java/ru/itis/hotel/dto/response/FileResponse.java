package ru.itis.hotel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {

    private UUID uuid;
    private String storageFileName;
    private String originalFileName;
    private String description;
    private Long size;
    private String contentType;
}
