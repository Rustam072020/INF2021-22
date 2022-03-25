package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class File {
    private Integer id;
    private String fileName;
    private String fileMimeType;
    private Integer fileSize;
    private String contentDisposition;
}
