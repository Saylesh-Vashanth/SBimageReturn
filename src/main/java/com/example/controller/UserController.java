package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.model.UserEntity;
import com.example.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/{username}")
    public UserEntity getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value= "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addUser(@RequestBody UserEntity entity) {
		return userService.addUser(entity);
	}
   
    @PutMapping(value = "/updateUser/{username}")
	public String updateUser(@PathVariable String username,  @RequestBody UserEntity entity) {
		return userService.updateUser(username, entity);
	}
}