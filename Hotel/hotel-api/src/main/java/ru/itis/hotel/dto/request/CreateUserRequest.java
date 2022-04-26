package ru.itis.hotel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.hotel.validation.annotation.ValidAge;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest extends UserRequest {

    private String fullName;

    @ValidAge
    private LocalDate birthDay;
}
