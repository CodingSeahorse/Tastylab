package com.codingseahorse.tastylab.requestsModels;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
