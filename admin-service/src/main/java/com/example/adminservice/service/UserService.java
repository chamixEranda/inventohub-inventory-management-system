package com.example.adminservice.service;

import com.example.adminservice.entity.User;
import com.example.adminservice.repository.UserRepository;
import org.hibernate.internal.util.StringHelper;
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

    public List<User> getAllUserDetails(){
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

    public ResponseData<User> authentication(String email, String password){
        ResponseData<User> resposne = new ResponseData<User>();

        User user = userRepository.findByEmailAndPassword(email,password);
        if (user != null){
            resposne.setData(user);
            resposne.setStatus(true);
            resposne.setMessage( "You are logged in!");
        }else {
            resposne.setStatus(false);
            resposne.setMessage("Sorry!, Unauthorized Access!");
        }
        return resposne;
    }


    public ResponseData<User> getUserDetails(int id){
        ResponseData<User> resposne = new ResponseData<User>();

         Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            resposne.setData(user.get());
            resposne.setStatus(true);
            resposne.setMessage( "User found");
        }else {
            resposne.setStatus(false);
            resposne.setMessage("Not user found!");
        }
        return  resposne;
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
