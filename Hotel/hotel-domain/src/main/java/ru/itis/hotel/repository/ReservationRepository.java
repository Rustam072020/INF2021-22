package ru.itis.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.hotel.model.ApartmentEntity;
import ru.itis.hotel.model.ReservationEntity;
import ru.itis.hotel.model.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

    @Query("select distinct r.apartment.uuid from ReservationEntity as r where (r.checkInDate < :checkInDate and r.checkOutDate >= :checkInDate) or (r.checkInDate <= :checkOutDate and r.checkOutDate > :checkOutDate)")
    List<UUID> searchBookedApartmentsAtSpecificTime(@Param("checkInDate")LocalDate checkInDate,
                                                               @Param("checkOutDate")LocalDate checkOutDate);

    List<ReservationEntity> findByClient(UUID user);

}
