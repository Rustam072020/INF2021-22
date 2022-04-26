package ru.itis.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.hotel.enums.EmailStatus;
import ru.itis.hotel.enums.Role;
import ru.itis.hotel.enums.UserState;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "account")
public class UserEntity extends AbstractEntity{

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private LocalDate birthDay;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private UserState state;

    @Enumerated(value = EnumType.STRING)
    private EmailStatus emailStatus;

    @ManyToMany(mappedBy = "administrators")
    private List<HotelEntity> hotels;
}
