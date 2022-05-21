package ru.itis.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.hotel.enums.ReservationState;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "reservation")
public class ReservationEntity extends AbstractEntity {

    @Column(name = "client_id")
    private UUID client;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartment;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private ReservationState state;


}
