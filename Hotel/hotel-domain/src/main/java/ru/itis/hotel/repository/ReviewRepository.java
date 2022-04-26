package ru.itis.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hotel.model.ReviewEntity;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
}
