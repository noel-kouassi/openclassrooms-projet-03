package com.openclassroom.rental.service;

import com.openclassroom.rental.dto.LoginDto;
import com.openclassroom.rental.dto.RegisterDto;

public interface UserService {

    String saveUser(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
