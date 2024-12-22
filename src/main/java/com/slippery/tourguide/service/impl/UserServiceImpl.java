package com.slippery.tourguide.service.impl;

import com.slippery.tourguide.dto.UserDto;
import com.slippery.tourguide.models.User;
import com.slippery.tourguide.repository.UserRepository;
import com.slippery.tourguide.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto createNewUser(User user, MultipartFile profileImage) throws IOException {
        UserDto response =new UserDto();
        User existingUser =repository.findUserByUsername(user.getUsername());
        if(existingUser ==null){
            User newUser =new User();
            newUser.setCreatedOn(LocalDateTime.now());
            newUser.setActive(false);
            newUser.setEmail(user.getEmail());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setRole(user.getRole());
            newUser.setUsername(user.getUsername());
            newUser.setProfilePhoto(profileImage.getBytes());
            repository.save(newUser);
            response.setMessage("user "+user.getUsername()+" created successfully");
            response.setUser(newUser);
            response.setStatusCode(50);
        }else {
            response.setErrorMessage("user already has an account");
            response.setStatusCode(50);
        }
        return response;
    }

    @Override
    public UserDto updateUser(User user) {
        return null;
    }

    @Override
    public UserDto deleteUser(Long userId) {
        return null;
    }

    @Override
    public UserDto findUserById(Long userId) {
        return null;
    }

    @Override
    public UserDto findAllUsers() {
        return null;
    }

    @Override
    public UserDto deleteAllUsers() {
        return null;
    }
}
