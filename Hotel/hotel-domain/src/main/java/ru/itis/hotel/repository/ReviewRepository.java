package ru.itis.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hotel.model.ApartmentEntity;
import ru.itis.hotel.model.ReviewEntity;

import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {

    Optional<ReviewEntity> findByClientAndApartments(UUID client, ApartmentEntity apartment);
}
