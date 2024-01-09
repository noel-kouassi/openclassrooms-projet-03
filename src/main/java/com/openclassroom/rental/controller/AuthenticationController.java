package com.openclassroom.rental.controller;

import com.openclassroom.rental.dto.RegisterDto;
import com.openclassroom.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto){
        userService.saveUser(registerDto);
        String message = "User created successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
