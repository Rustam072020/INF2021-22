package ru.itis.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "review",
        uniqueConstraints = @UniqueConstraint(columnNames = {"apartment_id","client_id"}))
public class ReviewEntity extends AbstractEntity {

    @Column(nullable = false)
    private Integer rating;

    private String flaws;
    private String benefits;
    private String description;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartments;

    @Column(name = "client_id")
    private UUID client;


}
