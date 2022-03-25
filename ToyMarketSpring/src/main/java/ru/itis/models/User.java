package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String lastName;
    private String avatar;

}
