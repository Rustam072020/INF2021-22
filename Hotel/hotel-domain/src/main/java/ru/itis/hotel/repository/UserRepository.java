package ru.itis.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hotel.model.UserEntity;

import java.awt.image.BufferedImageOp;
import java.nio.channels.FileChannel;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findOneByEmail(String email);
}
