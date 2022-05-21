package ru.itis.hotel.repository;

import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hotel.enums.ApartmentLevel;
import ru.itis.hotel.enums.ApartmentState;
import ru.itis.hotel.model.ApartmentEntity;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, UUID> {

    Page<ApartmentEntity> findAllByUuidNotInAndLevelEqualsAndCostBetweenAndStateEquals(List<UUID> reservations, ApartmentLevel level, Integer from, Integer to, ApartmentState state, Pageable pageable);

    Page<ApartmentEntity> findAllByUuidIsNotInAndCostBetweenAndStateEquals(List<UUID> reservations, Integer minCost, Integer maxCost, ApartmentState state, Pageable pageable);
}
