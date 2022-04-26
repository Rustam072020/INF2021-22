package ru.itis.hotel.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "hotel")
public class HotelEntity extends AbstractEntity {

    @Column(nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_administrators",
            joinColumns = {@JoinColumn(name = "hotel_id", referencedColumnName = "uuid")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "uuid")}
    )
    private Set<UserEntity> administrators;

    private Double rating;

    @OneToMany(mappedBy = "hotel")
    private Set<FileEntity> files;
}
