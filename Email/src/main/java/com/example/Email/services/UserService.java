package com.example.Email.services;

import com.example.Email.dtos.request.CreateUserRequest;
import com.example.Email.dtos.response.CreateUserResponse;

public interface UserService {
    CreateUserResponse register(CreateUserRequest request);


}
