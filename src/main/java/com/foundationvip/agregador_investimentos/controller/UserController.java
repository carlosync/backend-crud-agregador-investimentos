package com.foundationvip.agregador_investimentos.controller;

import com.foundationvip.agregador_investimentos.controller.dto.CreateUserDto;
import com.foundationvip.agregador_investimentos.controller.dto.ReplaceUserDto;
import com.foundationvip.agregador_investimentos.domain.Users;
import com.foundationvip.agregador_investimentos.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody CreateUserDto createUserDto){
        var userId = this.userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable("userId") String userId){
        var user = this.userService.findUserById(userId);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        var users  = this.userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteById(@PathVariable("userId") String userId){
        this.userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> replecaUserById(@PathVariable("userId") String  userId, @RequestBody ReplaceUserDto replaceUserDto){
        this.userService.replaceUserById(userId, replaceUserDto);
        return ResponseEntity.noContent().build();
    }
}
