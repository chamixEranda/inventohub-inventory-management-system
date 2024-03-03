package com.example.adminservice.controller;

import com.example.adminservice.entity.User;
import com.example.adminservice.service.LoginRequest;
import com.example.adminservice.service.ResponseData;
import com.example.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private LoginRequest loginRequest;

    @GetMapping(path = "/api/v1/users")
    public List<User> getUserDetails(){
        return userService.getUserDetails();
    }

    @PostMapping(path = "/api/v1/users/login")
    public ResponseEntity<String> authentication(@RequestBody LoginRequest loginRequest){
        return userService.authentication(loginRequest.getEmail(),loginRequest.getPassword());
    }

    @PutMapping(path = "/api/v1/users/{userId}")
    public ResponseEntity<String> updateProfile(@PathVariable Integer userId, @RequestBody User user){
        return userService.updateProfile(userId, user);
    }
}
