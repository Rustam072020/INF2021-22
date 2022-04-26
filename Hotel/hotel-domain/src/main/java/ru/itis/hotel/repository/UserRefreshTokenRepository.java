package ru.itis.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hotel.model.UserRefreshTokenEntity;

import java.util.UUID;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshTokenEntity, UUID> {

}
