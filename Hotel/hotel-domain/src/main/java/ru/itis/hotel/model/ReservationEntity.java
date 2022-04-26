package ru.itis.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.hotel.enums.ReservationState;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "reservation")
public class ReservationEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartment;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private ReservationState state;


}
