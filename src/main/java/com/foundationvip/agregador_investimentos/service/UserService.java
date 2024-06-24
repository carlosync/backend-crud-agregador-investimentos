package com.foundationvip.agregador_investimentos.service;

import com.foundationvip.agregador_investimentos.controller.dto.CreateUserDto;
import com.foundationvip.agregador_investimentos.controller.dto.ReplaceUserDto;
import com.foundationvip.agregador_investimentos.domain.Users;
import com.foundationvip.agregador_investimentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){
        var entity = new Users(UUID.randomUUID(), createUserDto.username(), createUserDto.email(),
                                createUserDto.password(), Instant.now(),null);

        var userSaved = this.userRepository.save(entity);
        return userSaved.getUserId();
    }

    public Optional<Users> findUserById(String userId){
        return this.userRepository.findById(UUID.fromString(userId));
    }

    public List<Users> findAllUsers(){
        return this.userRepository.findAll();
    }

    public void deleteUserById(String userId){
        var id = UUID.fromString(userId);
        var userExists = this.userRepository.existsById(id);
        if (userExists){
            this.userRepository.deleteById(id);
        }
    }

    public void replaceUserById(String userId, ReplaceUserDto replaceUserDto){
        var id = UUID.fromString(userId);
        var userEntity = this.userRepository.findById(id);
        if (userEntity.isPresent()){
            var user = userEntity.get();
            if(replaceUserDto.username().isBlank()){
                user.setUsername(replaceUserDto.username());
            }
            if(replaceUserDto.password().isBlank()){
                user.setPassword(replaceUserDto.password());
            }
            this.userRepository.save(user);
        }
    }
}
