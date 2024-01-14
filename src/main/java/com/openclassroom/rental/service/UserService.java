package com.openclassroom.rental.service;

import com.openclassroom.rental.dto.LoginDto;
import com.openclassroom.rental.dto.RegisterDto;
import com.openclassroom.rental.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    String saveUser(RegisterDto registerDto);

    String login(LoginDto loginDto);

    UserDto getUserFromToken(HttpServletRequest httpServletRequest);

    UserDto getUserById(Long userId);
}
