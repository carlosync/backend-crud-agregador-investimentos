package com.foundationvip.agregador_investimentos.controller;

import com.foundationvip.agregador_investimentos.domain.Users;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody String user){
        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String id){
        return null;
    }
}
