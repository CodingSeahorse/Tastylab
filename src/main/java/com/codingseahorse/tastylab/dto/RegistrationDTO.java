package com.codingseahorse.tastylab.dto;

import com.codingseahorse.tastylab.model.member.Gender;
import com.codingseahorse.tastylab.model.member.MembershipRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationDTO {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer age;
    private final Gender gender;
    private MembershipRole membershipRole;
}
