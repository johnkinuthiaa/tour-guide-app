package com.slippery.tourguide.service.impl;

import com.slippery.tourguide.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
}
