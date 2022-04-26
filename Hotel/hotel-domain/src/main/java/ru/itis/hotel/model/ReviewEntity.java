package ru.itis.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "review",
        uniqueConstraints = @UniqueConstraint(columnNames = {"apartment_id","user_id"}))
public class ReviewEntity extends AbstractEntity {

    @Column(nullable = false)
    private Integer rating;

    private String flaws;
    private String benefits;
    private String description;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;


}
