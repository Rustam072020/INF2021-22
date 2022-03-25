package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilesForm {
    private String fileName;
    private String fileMimeType;
    private Integer fileSize;
    private String contentDisposition;
}
