package ru.itis.hotel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.hotel.validation.annotation.ValidPassword;

import javax.validation.constraints.Email;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @Email
    private String email;

    @ValidPassword
    private String password;
}
