package com.example.Email.controllers;

import com.example.Email.dtos.request.CreateUserRequest;
import com.example.Email.dtos.request.UpdateUserRequest;
import com.example.Email.exceptions.UserNotFoundException;
import com.example.Email.exceptions.UserRegistrationFailedException;
import com.example.Email.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("User/Register")
    public Object register(@RequestBody CreateUserRequest request){
        try {
            return userService.register(request);
        }
        catch (UserRegistrationFailedException e){
            return e.getMessage();
        }
    }

    @GetMapping("/User/FindById/{id}")
    public Object findUserById(@PathVariable long id) {
        try {
            return userService.findUserById(id);
        } catch (UserNotFoundException e) {
            return e.getMessage();
        }
    }

    @PostMapping("User/DeleteById/{id}")
    public Object deleteUserById(@PathVariable long id){
        try {
            return userService.deleteUserById(id);
        }
        catch (UserNotFoundException e){
            return e.getMessage();
        }
    }

    @PostMapping("User/UpdateUser")
    public Object updateUser(@RequestBody UpdateUserRequest request){
        try {
            return userService.updateUser(request);
        } catch (UserNotFoundException e) {
            return e.getMessage();
        }
    }
//
//    @PostMapping("User/login")
//    public Object login(@RequestBody LoginUserRequest request){
//        try {
//
//        }
//        catch ()
//    }
}
