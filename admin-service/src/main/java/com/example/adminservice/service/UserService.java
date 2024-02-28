package com.example.adminservice.service;

import com.example.adminservice.entity.User;
import com.example.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registration(User user){
        if (!isValidEmail(user.getEmail())){
            return "Invalid email address!";
        }

        if (!isUniqueEmail(user.getEmail())) {
            return "Email already exists!";
        }

        if (!isValidPhoneNumber(user.getPhone_number())){
            return "Invalid phone number! It should have 10 digits.";
        }

        if (!isValidPassword(user.getPassword())) {
            return "Password should have minimum 6 characters!";
        }

        userRepository.save(user);
        return "User registered successfully!";
    }

    private boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return StringUtils.hasText(email) && email.matches(emailRegex);
    }

    private boolean isUniqueEmail(String email) {
        // Check if email already exists in the database
        return userRepository.findByEmail(email) == null;
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

    public String authentication(String email, String password){
        User user = userRepository.findByEmailAndPassword(email,password);
        if (user != null){
            return "You are logged in!";
        }else {
            return "Sorry!, Credentials are wrong!";
        }
    }
}
