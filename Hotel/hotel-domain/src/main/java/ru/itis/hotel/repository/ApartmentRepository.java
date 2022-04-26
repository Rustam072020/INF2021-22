package ru.itis.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hotel.model.ApartmentEntity;

import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, UUID> {
}
