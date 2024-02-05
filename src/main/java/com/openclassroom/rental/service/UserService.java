package com.openclassroom.rental.service;

import com.openclassroom.rental.dto.input.LoginDto;
import com.openclassroom.rental.dto.input.RegisterDto;
import com.openclassroom.rental.dto.response.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    String saveUser(RegisterDto registerDto);

    String login(LoginDto loginDto);

    UserDto getUserFromToken(HttpServletRequest httpServletRequest);

    UserDto getUserById(Long userId);
}
