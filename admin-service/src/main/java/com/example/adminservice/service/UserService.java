package com.example.adminservice.service;

import com.example.adminservice.entity.User;
import com.example.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUserDetails(){
        return userRepository.findAll();
    }

    private boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return StringUtils.hasText(email) && email.matches(emailRegex);
    }

    private boolean isValidPhoneNumber(int phoneNumber) {
        // Validate phone number has 10 digits
        String phoneNumberStr = String.valueOf(phoneNumber);
        return phoneNumberStr.length() == 9;
    }

    private boolean isValidPassword(String password) {
        // Validate password has minimum 6 characters
        return StringUtils.hasText(password) && password.length() >= 6;
    }

    public ResponseEntity<String> authentication(String email, String password){
        ResponseData resposne = new ResponseData();
        User user = userRepository.findByEmailAndPassword(email,password);
        if (user != null){
            String message = "You are logged in!";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else {
            String message = "Sorry!, Unauthorized Access!";
            return new ResponseEntity<>(message,HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<String> updateProfile(Integer userId, User user){
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null){
            String message = "User not found!";
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
        if (!isValidEmail(user.getEmail())) {
            String message = "Invalid email address!";
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }

        if (!isValidPhoneNumber(user.getPhone_number())) {
            String message = "Invalid phone number! It should have 10 digits.";
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }

        if (!isValidPassword(user.getPassword())) {
            String message = "Password should have minimum 6 characters!";
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
        existingUser.setF_name(user.getF_name());
        existingUser.setL_name(user.getL_name());
        existingUser.setCompany_name(user.getCompany_name());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhone_number(user.getPhone_number());

        userRepository.save(existingUser);
        String message = "Profile Updated Successfully!";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
