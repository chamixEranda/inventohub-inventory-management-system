package com.example.adminservice.controller;

import com.example.adminservice.entity.User;
import com.example.adminservice.service.LoginRequest;
import com.example.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private LoginRequest loginRequest;

    @PostMapping(path = "/api/v1/users")
    public String registration(@RequestBody User user){
        String registrationResult = userService.registration(user);
        return registrationResult;
    }

    @PostMapping(path = "/api/v1/users/login")
    public String authentication(@RequestBody LoginRequest loginRequest){
        return userService.authentication(loginRequest.getEmail(),loginRequest.getPassword());
    }

    @PutMapping(path = "/api/v1/users/{userId}")
    public String updateProfile(@PathVariable Integer userId,@RequestBody User user){
        return userService.updateProfile(userId, user);
    }
}
