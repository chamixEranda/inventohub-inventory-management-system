package com.example.adminservice.service;

import com.example.adminservice.entity.User;
import com.example.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResponseData authentication(String email, String password){
        ResponseData resposne = new ResponseData();
        User user = userRepository.findByEmailAndPassword(email,password);
        if (user != null){
            resposne.setMessage("You are logged in!");
            resposne.setStatus_code(200);
            System.out.println(resposne.getMessage());
            System.out.println(resposne.getData());
            System.out.println(resposne.getStatus_code());
            return resposne;
        }else {
            resposne.setMessage("Sorry!, Unauthorized Access!");
            resposne.setStatus_code(401);
            System.out.println(resposne);
            return resposne;
        }
    }

    public String updateProfile(Integer userId, User user){
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null){
            return "User not found!";
        }
        if (!isValidEmail(user.getEmail())) {
            return "Invalid email address!";
        }

        if (!isValidPhoneNumber(user.getPhone_number())) {
            return "Invalid phone number! It should have 10 digits.";
        }

        if (!isValidPassword(user.getPassword())) {
            return "Password should have minimum 6 characters!";
        }
        existingUser.setF_name(user.getF_name());
        existingUser.setL_name(user.getL_name());
        existingUser.setCompany_name(user.getCompany_name());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhone_number(user.getPhone_number());

        userRepository.save(existingUser);
        return "Profile Updated Successfully!";
    }
}
