package ru.itis.hotel.mapper;

import org.mapstruct.Mapper;
import ru.itis.hotel.dto.response.FileResponse;
import ru.itis.hotel.model.FileEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileResponse toFIleResponse(FileEntity fileEntity);

    List<FileResponse> toFileResponseList(List<FileEntity> files);
}
