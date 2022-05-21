package ru.itis.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hotel.model.UserEntity;
import ru.itis.hotel.model.UserRefreshTokenEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshTokenEntity, UUID> {

}
