package com.example.adminservice.controller;

import com.example.adminservice.entity.User;
import com.example.adminservice.request_models.LoginRequest;
import com.example.adminservice.response_models.ResponseData;
import com.example.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    private LoginRequest loginRequest;

    @GetMapping(path = "/api/v1/users")
    public List<User> getAllUserDetails(){
        return userService.getAllUserDetails();
    }

    @GetMapping(path = "/api/v1/users/{id}")
    public ResponseData<User> getUserDetails(@PathVariable Integer id){
        return userService.getUserDetails(id);
    }

    @PostMapping(path = "/api/v1/users/login")
    public ResponseData<User> authentication(@RequestBody LoginRequest loginRequest){
//        System.out.println("This is "+loginRequest.getEmail()+" "+loginRequest.getPassword());
        return userService.authentication(loginRequest.getEmail(),loginRequest.getPassword());
    }

    @PutMapping(path = "/api/v1/users/{userId}")
    public ResponseData<User> updateProfile(@PathVariable Integer userId, @RequestBody User user){
        return userService.updateProfile(userId, user);
    }
}
