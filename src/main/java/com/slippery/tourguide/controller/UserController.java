package com.slippery.tourguide.controller;

import com.slippery.tourguide.dto.UserDto;
import com.slippery.tourguide.models.User;
import com.slippery.tourguide.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> createNewUser(@RequestBody User user) throws IOException {
        return ResponseEntity.ok(service.createNewUser(user));
    }
    @PutMapping("/user/profile")
    public ResponseEntity<UserDto> updateUserProfile(@RequestParam Long userId,@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(service.updateUserProfileImage(userId, image));
    }
    @PutMapping("/update/user")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user,@RequestParam Long userId){
        return ResponseEntity.ok(service.updateUser(user, userId));
    }
//    UserDto deleteUser(Long userId);
    @GetMapping("/get/user")
    public ResponseEntity<UserDto> findUserById(@RequestParam Long userId){
        return ResponseEntity.ok(service.findUserById(userId));
    }
    @GetMapping("/get/all")
    public ResponseEntity<UserDto> findAllUsers(){
        return ResponseEntity.ok(service.findAllUsers());
    }
    @DeleteMapping("/delete/user/id")
    public ResponseEntity<UserDto> deleteUser(@RequestParam Long userId){
        return ResponseEntity.ok(service.deleteUser(userId));
    }
}
