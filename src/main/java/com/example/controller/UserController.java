package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.UserEntity;
import com.example.service.UserService;

import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping(value = "/addUser", produces = "application/json")
    public String addUser(@RequestBody UserEntity entity) {
        return userService.addUser(entity);
    }

    @PutMapping(value = "/updateUser/{username}", consumes = "application/json")
    public String updateUser(@PathVariable String username, @RequestBody UserEntity entity) {
        return userService.updateUser(username, entity);
    }

    @GetMapping("/image/{username}")
    public ResponseEntity<?> getUserImage(@PathVariable String username) {
        UserEntity user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (user.getProfileimage() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User image is not found");
        }

        try {
            Path imagePath = Paths.get("src/main/resources/static/", user.getProfileimage());
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
