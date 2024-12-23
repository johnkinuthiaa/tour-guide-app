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
    public UserDto createNewUser(User user)  {
        UserDto response =new UserDto();
        User existingUser =repository.findUserByUsername(user.getUsername());
        if(existingUser ==null){
            User newUser =new User();
            newUser.setCreatedOn(LocalDateTime.now());
            newUser.setIsActive(false);
            newUser.setEmail(user.getEmail());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setRole(user.getRole());
            newUser.setUsername(user.getUsername());
            newUser.setProfilePhoto(null);
            repository.save(newUser);
            response.setMessage("user "+user.getUsername()+" created successfully");
            response.setUser(newUser);
            response.setStatusCode(200);
        }else {
            response.setErrorMessage("user already has an account");
            response.setStatusCode(500);
        }
        return response;
    }

    @Override
    public UserDto updateUserProfileImage(Long userId, MultipartFile image) throws IOException {
        UserDto response =new UserDto();
        Optional<User> existingUser =repository.findById(userId);
        if(existingUser.isEmpty()) {
            response.setErrorMessage("user does not exist");
            response.setStatusCode(200);
            return response;
        }else{
            existingUser.get()
                    .setProfilePhoto(
                            image.getBytes());
            repository.save(existingUser.get());
            response.setErrorMessage("profile image updated");
            response.setStatusCode(200);
        }
        return response;
    }

    @Override
    public UserDto updateUser(User user,Long userId) {
        UserDto response =new UserDto();
        Optional<User> existingUser =repository.findById(userId);
        if(existingUser.isEmpty()){
            response.setErrorMessage("user already has an account");
            response.setStatusCode(200);
            return response;
        }else{
            User toUpdate =existingUser.get();
            toUpdate.setProfilePhoto(user.getProfilePhoto() !=null? user.getProfilePhoto() : existingUser.get().getProfilePhoto());

            toUpdate.setUsername(user.getUsername() !=null? user.getUsername():existingUser.get().getUsername());
            toUpdate.setFirstName(user.getFirstName() !=null? user.getFirstName(): existingUser.get().getFirstName());
            toUpdate.setLastName(user.getLastName() !=null? user.getLastName() : existingUser.get().getLastName());
            toUpdate.setEmail(user.getEmail() !=null?user.getEmail(): existingUser.get().getEmail());
            toUpdate.setIsActive(user.getIsActive() !=null?user.getIsActive(): existingUser.get().getIsActive());
            toUpdate.setRole(user.getRole() !=null?user.getRole():existingUser.get().getRole());
            toUpdate.setPhoneNumber(user.getPhoneNumber() !=null? user.getPhoneNumber() : existingUser.get().getPhoneNumber());
            toUpdate.setPassword(user.getPassword() !=null?passwordEncoder.encode(user.getPassword()): existingUser.get().getPassword());
            repository.save(toUpdate);
            response.setMessage("user "+existingUser.get().getUsername() +" was updated successfully");
            response.setStatusCode(200);
        }
        return response;
    }

    @Override
    public UserDto deleteUser(Long userId) {
        UserDto response =new UserDto();
        Optional<User> existingUser =repository.findById(userId);
        if(existingUser.isEmpty()) {
            response.setErrorMessage("user does not exist!");
            response.setStatusCode(404);
            return response;
        }else{
            repository.deleteById(userId);
            repository.delete(existingUser.get());
            response.setErrorMessage("user "+existingUser.get().getUsername()+"'s account was deleted successfully");
            response.setStatusCode(204);
        }
        return response;
    }

    @Override
    public UserDto findUserById(Long userId) {
        UserDto response =new UserDto();
        Optional<User>user =repository.findById(userId);
        if (user.isEmpty()){
            response.setErrorMessage("user not found");
            response.setStatusCode(404);
            return response;
        }
        response.setUser(user.get());
        response.setMessage("user with id "+userId);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserDto findAllUsers() {
        UserDto response =new UserDto();
        response.setUserList(repository.findAll());
        response.setMessage("All users");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserDto deleteAllUsers() {
        return null;
    }
}
