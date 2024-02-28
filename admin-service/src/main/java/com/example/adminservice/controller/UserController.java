package com.example.adminservice.controller;

import com.example.adminservice.entity.User;
import com.example.adminservice.service.LoginRequest;
import com.example.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
