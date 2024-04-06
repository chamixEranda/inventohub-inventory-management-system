package com.example.adminservice.service;

import com.example.adminservice.entity.User;
import com.example.adminservice.repository.UserRepository;
import com.example.adminservice.response_models.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUserDetails() {
        return userRepository.findAll();
    }

    private boolean isValidEmail(String email) {
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

    public ResponseData<User> authentication(String email, String password) {
        ResponseData<User> resposne = new ResponseData<User>();

        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            resposne.setData(user);
            resposne.setStatus(true);
            resposne.setMessage("You are logged in!");
        } else {
            resposne.setStatus(false);
            resposne.setMessage("Sorry!, Unauthorized Access!");
        }
        return resposne;
    }


    public ResponseData<User> getUserDetails(int id) {
        ResponseData<User> resposne = new ResponseData<User>();

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            resposne.setData(user.get());
            resposne.setStatus(true);
            resposne.setMessage("User found");
        } else {
            resposne.setStatus(false);
            resposne.setMessage("Not user found!");
        }
        return resposne;
    }

    public ResponseData<User> updateProfile(Integer userId, User user) {

        ResponseData responseData = new ResponseData<User>();
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null) {
            responseData.setStatus(false);
            responseData.setMessage("User not found!");
            return responseData;
        }
        if (!isValidEmail(user.getEmail())) {
            responseData.setStatus(false);
            responseData.setMessage("Invalid email address!");
            return responseData;
        }

        if (!isValidPhoneNumber(user.getPhone_number())) {
            responseData.setStatus(false);
            responseData.setMessage("Invalid phone number! It should have 10 digits.");
            return responseData;
        }

        if (!isValidPassword(user.getPassword())) {

            responseData.setStatus(false);
            responseData.setMessage("Password should have minimum 6 characters!");
            return responseData;
        }
        existingUser.setF_name(user.getF_name());
        existingUser.setL_name(user.getL_name());
        existingUser.setCompany_name(user.getCompany_name());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhone_number(user.getPhone_number());

        User updateUser = userRepository.save(existingUser);
        responseData.setStatus(true);
        responseData.setData(updateUser);
        responseData.setMessage("Profile Updated Successfully!");
        return responseData;
    }


}
