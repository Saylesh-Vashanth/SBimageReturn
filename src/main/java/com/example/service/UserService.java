package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.UserEntity;
import com.example.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    
    public String addUser(UserEntity user) {
        UserEntity existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            return "User with username " + user.getUsername() + " already exists";
        }
        userRepository.save(user);
        return "User added successfully";
    }

    public String updateUser(String username, UserEntity updatedUser) {
        UserEntity existingUser = userRepository.findByUsername(username);
        if (existingUser == null) {
            return "User with username " + username + " does not exist";
        }
       
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        userRepository.save(existingUser);
        return "User updated successfully";
    }


   
}
