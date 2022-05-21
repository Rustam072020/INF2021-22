package ru.itis.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.hotel.enums.ApartmentLevel;
import ru.itis.hotel.enums.ApartmentState;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "apartment")
public class ApartmentEntity extends AbstractEntity {

    @Column(name = "apartment_number")
    private Integer apartmentNumber;

    private Integer cost;

    @Enumerated(value = EnumType.STRING)
    private ApartmentState state;

    @Enumerated(value = EnumType.STRING)
    private ApartmentLevel level;

    private Double rating = 0.0;

    @Column(name = "client_amount")
    private Integer clientAmount = 0;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

    @OneToMany(mappedBy = "apartment")
    private Set<FileEntity> files;
}
