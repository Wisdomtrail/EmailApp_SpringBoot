package com.example.Email.services;

import com.example.Email.data.models.User;
import com.example.Email.data.repositories.UserRepository;
import com.example.Email.dtos.request.CreateUserRequest;
import com.example.Email.dtos.request.LoginUserRequest;
import com.example.Email.dtos.request.UpdateUserRequest;
import com.example.Email.dtos.response.*;
import com.example.Email.exceptions.UserNotFoundException;
import com.example.Email.exceptions.UserRegistrationFailedException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.example.Email.util.ExceptionUtils.*;
import static com.example.Email.util.ResponseUtils.*;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public CreateUserResponse register(CreateUserRequest request) throws UserRegistrationFailedException {
        User user = new User();
        modelMapper.map(request, user);
        if (userExist(user.getPhoneNumber())){
            throw new UserRegistrationFailedException(String.format(USER_REGISTRATION_FAILED, user.getFirstName()));
        }
        userRepository.save(user);
        return buildUserRegistrationResponse(user.getId());
    }

    private boolean userExist(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        return user != null;
    }

    @Override
    public UserResponse findUserById(long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, id)));
        return buildUserResponse(user.getId(), user.getFirstName() + " " + user.getLastName());
    }

    @Override
    public DeleteUserResponse deleteUserById(long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, id)));
        userRepository.delete(user);
        return buildDeleteUserResponse(user.getId());
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest request) throws UserNotFoundException {
       Optional<User> optionalUser =  userRepository.findById(request.getId());
       User user = optionalUser.orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, request.getId())));
       user.setFirstName(request.getFirstName());
       user.setLastName(request.getLastName());
       user.setPhoneNumber(request.getPhoneNumber());
       return buildUpdateUserResponse(request.getId());
    }

    @Override
    public LoginResponse login(LoginUserRequest request) {
        return null;
    }


    private UpdateUserResponse buildUpdateUserResponse(Long id) {
        UpdateUserResponse response = new UpdateUserResponse();
        response.setMessage(String.format(USER_INFORMATION_UPDATED, id));
        response.setId(id);
        return response;
    }

    private DeleteUserResponse buildDeleteUserResponse(Long id) {
        DeleteUserResponse response = new DeleteUserResponse();
        response.setMessage(String.format(USER_DELETED, id));
        response.setId(id);
        return response;
    }

    private CreateUserResponse buildUserRegistrationResponse(Long userId) {
        CreateUserResponse response = new CreateUserResponse();
        response.setMessage(USER_REGISTRATION_SUCCESSFUL);
        response.setId(userId);
        return response;
    }

    private UserResponse buildUserResponse(Long userId, String fullName) {
        UserResponse userResponse = new UserResponse();
        userResponse.setFullName(fullName);
        userResponse.setId(userId);
        return userResponse;
    }
}