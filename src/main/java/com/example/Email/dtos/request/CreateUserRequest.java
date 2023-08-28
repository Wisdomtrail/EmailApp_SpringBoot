package com.example.Email.dtos.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
