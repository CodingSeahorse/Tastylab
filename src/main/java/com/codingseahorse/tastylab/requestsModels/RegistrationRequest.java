package com.codingseahorse.tastylab.requestsModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer age;
    private final String gender;
}
