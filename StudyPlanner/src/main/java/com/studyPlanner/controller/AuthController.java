package com.studyPlanner.controller;


import com.studyPlanner.entity.User;
import com.studyPlanner.security.JwtUtil;
import com.studyPlanner.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        User saveUser = userService.registerUser(user);
        return ResponseEntity.ok(Map.of("message","User registered successfully","user",saveUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        Optional<User> foundUser = userService.findByUsername(user.getUsername());
        if(foundUser.isPresent() && userService.validatePassword(user.getPassword(),foundUser.get().getPassword())){
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(Map.of("token",token));
        }
        return ResponseEntity.status(401).body(Map.of("error","Invalid credentials"));
    }
}
