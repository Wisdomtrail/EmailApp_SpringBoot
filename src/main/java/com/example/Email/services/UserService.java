package com.example.Email.services;

import com.example.Email.data.models.User;
import com.example.Email.dtos.request.CreateUserRequest;
import com.example.Email.dtos.request.LoginUserRequest;
import com.example.Email.dtos.request.UpdateUserRequest;
import com.example.Email.dtos.response.*;
import com.example.Email.exceptions.UserNotFoundException;
import com.example.Email.exceptions.UserRegistrationFailedException;

public interface UserService {
    CreateUserResponse register(CreateUserRequest request) throws UserRegistrationFailedException;

    UserResponse findUserById(long id) throws UserNotFoundException;

    DeleteUserResponse deleteUserById(long id) throws UserNotFoundException;

    UpdateUserResponse updateUser(UpdateUserRequest request) throws UserNotFoundException;


    LoginResponse login(LoginUserRequest request);
}
