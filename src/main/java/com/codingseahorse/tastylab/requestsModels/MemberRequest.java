package com.codingseahorse.tastylab.requestsModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String gender;
}
