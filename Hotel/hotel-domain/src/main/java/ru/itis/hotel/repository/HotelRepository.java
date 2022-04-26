package ru.itis.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hotel.model.HotelEntity;

import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {
}
