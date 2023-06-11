package com.example.Email.services;

import com.example.Email.data.models.User;
import com.example.Email.data.repositories.UserRepository;
import com.example.Email.dtos.request.CreateUserRequest;
import com.example.Email.dtos.response.CreateUserResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;


@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final ModelMapper modelMapper;
    private UserRepository userRepository;

    @Override
    public CreateUserResponse register(CreateUserRequest request) {
        User user = new User();
        modelMapper.map(request, User.class);
    }
}
