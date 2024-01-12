package com.openclassroom.rental.controller;

import com.openclassroom.rental.dto.JwtTokenResponse;
import com.openclassroom.rental.dto.LoginDto;
import com.openclassroom.rental.dto.RegisterDto;
import com.openclassroom.rental.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<JwtTokenResponse> registerUser(@Valid @RequestBody RegisterDto registerDto) {

        userService.saveUser(registerDto);
        LoginDto loginDto = new LoginDto();
        loginDto.setLogin(registerDto.getEmail());
        loginDto.setPassword(registerDto.getPassword());

        String userToken = userService.login(loginDto);
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
        jwtTokenResponse.setToken(userToken);

        return new ResponseEntity<>(jwtTokenResponse, HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtTokenResponse> loginUser(@Valid @RequestBody LoginDto loginDto) {
        String userToken = userService.login(loginDto);
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
        jwtTokenResponse.setToken(userToken);
        return new ResponseEntity<>(jwtTokenResponse, HttpStatus.OK);
    }
}
