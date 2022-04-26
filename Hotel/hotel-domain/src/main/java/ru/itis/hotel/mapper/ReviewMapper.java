package ru.itis.hotel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.hotel.dto.request.ReviewRequest;
import ru.itis.hotel.dto.response.ReviewResponse;
import ru.itis.hotel.model.ReviewEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewEntity toReviewEntity(ReviewRequest reviewRequest);

    @Mapping(source = "reviewEntity.uuid", target = "uuid")
    ReviewResponse toReviewResponse(ReviewEntity reviewEntity);

    List<ReviewResponse> toReviewResponseList(List<ReviewEntity> reviewEntities);
}
