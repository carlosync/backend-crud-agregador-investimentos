package com.foundationvip.agregador_investimentos.service;

import com.foundationvip.agregador_investimentos.controller.dto.CreateUserDto;
import com.foundationvip.agregador_investimentos.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserDto createUserDto){

    }
}
