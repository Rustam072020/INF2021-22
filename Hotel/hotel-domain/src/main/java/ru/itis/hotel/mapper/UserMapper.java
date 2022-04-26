package ru.itis.hotel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.hotel.dto.request.CreateUserRequest;
import ru.itis.hotel.dto.request.UserRequest;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.model.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "password", target = "hashPassword")
    UserEntity toUserEntity(UserRequest userRequest);

    @Mapping(source = "password", target = "hashPassword")
    UserEntity toUserEntity(CreateUserRequest createUserRequest);

    UserResponse toUserResponse(UserEntity user);

    List<UserEntity> toUsers(List<UserRequest> userRequests);

    List<UserEntity> toUserEntityList(List<CreateUserRequest> createUserRequests);

    List<UserResponse> toUserResponseList(List<UserEntity> users);
}
