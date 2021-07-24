package com.codingseahorse.tastylab.dto;

import com.codingseahorse.tastylab.model.member.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationDTO {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer age;
    private final Gender gender;
}
