package com.slippery.tourguide.service;

import com.slippery.tourguide.dto.UserDto;
import com.slippery.tourguide.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    UserDto createNewUser(User user) ;
    UserDto updateUserProfileImage(Long userId,MultipartFile image) throws IOException;

    UserDto updateUser(User user,Long userId);
    UserDto deleteUser(Long userId);
    UserDto findUserById(Long userId);
    UserDto findAllUsers();
    UserDto deleteAllUsers();

}
